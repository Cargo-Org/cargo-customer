import sys
import re
import os
import uuid

def generate_uuid(existing):
    while True:
        # Xcode UUIDs are 24-character hex strings
        u = uuid.uuid4().hex[:24].upper()
        if u not in existing:
            return u

def add_package(pbxproj_path, target_name, package_name, repo_url, version):
    if not os.path.exists(pbxproj_path):
        print(f"Error: {pbxproj_path} not found.")
        sys.exit(1)

    with open(pbxproj_path, 'r', encoding='utf-8') as f:
        content = f.read()

    # Gather all existing 24-character hex strings to avoid collision
    existing_uuids = set(re.findall(r'[A-F0-9]{24}', content))

    package_ref_uuid = generate_uuid(existing_uuids)
    product_dep_uuid = generate_uuid(existing_uuids | {package_ref_uuid})
    build_file_uuid = generate_uuid(existing_uuids | {package_ref_uuid, product_dep_uuid})

    print("Generated Xcode Unique Identifiers:")
    print(f"  Package Reference: {package_ref_uuid}")
    print(f"  Product Dependency: {product_dep_uuid}")
    print(f"  Build File:        {build_file_uuid}")

    # Check if package is already there
    if repo_url in content:
        print(f"Warning: Package with URL '{repo_url}' already exists in the project configuration.")
        return False

    # 1. PBXBuildFile Section
    build_file_entry = f"\t\t{build_file_uuid} /* {package_name} in Frameworks */ = {{isa = PBXBuildFile; productRef = {product_dep_uuid} /* {package_name} */; }};\n"
    if "/* Begin PBXBuildFile section */" in content:
        content = re.sub(r"(/\* Begin PBXBuildFile section \*/\r?\n)", rf"\1{build_file_entry}", content)
    else:
        # Create PBXBuildFile section dynamically right after "objects = {"
        new_section = (
            "/* Begin PBXBuildFile section */\n"
            + build_file_entry +
            "/* End PBXBuildFile section */\n\n"
        )
        if "objects = {" in content:
            content = re.sub(r"(objects = \{\r?\n)", rf"\1{new_section}", content)
        else:
            print("Error: Could not locate 'objects = {' section.")
            return False

    # 2. XCRemoteSwiftPackageReference Section
    pkg_ref_entry = (
        f"\t\t{package_ref_uuid} /* RemoteSwiftPackageReference \"{package_name}\" */ = {{\n"
        f"\t\t\tisa = XCRemoteSwiftPackageReference;\n"
        f"\t\t\trepositoryURL = \"{repo_url}\";\n"
        f"\t\t\trequirement = {{\n"
        f"\t\t\t\tkind = upToNextMajorVersion;\n"
        f"\t\t\t\tminimumVersion = {version};\n"
        f"\t\t\t}};\n"
        f"\t\t}};\n"
    )
    if "/* Begin XCRemoteSwiftPackageReference section */" in content:
        content = re.sub(r"(/\* Begin XCRemoteSwiftPackageReference section \*/\r?\n)", rf"\1{pkg_ref_entry}", content)
    else:
        new_section = (
            "/* Begin XCRemoteSwiftPackageReference section */\n"
            + pkg_ref_entry +
            "/* End XCRemoteSwiftPackageReference section */\n\n"
        )
        if "/* Begin XCBuildConfiguration section */" in content:
            content = re.sub(r"(/\* Begin XCBuildConfiguration section \*/)", rf"{new_section}\1", content)
        else:
            print("Error: Could not locate XCBuildConfiguration section.")
            return False

    # 3. XCSwiftPackageProductDependency Section
    prod_dep_entry = (
        f"\t\t{product_dep_uuid} /* {package_name} */ = {{\n"
        f"\t\t\tisa = XCSwiftPackageProductDependency;\n"
        f"\t\t\tpackage = {package_ref_uuid} /* RemoteSwiftPackageReference \"{package_name}\" */;\n"
        f"\t\t\tproductName = {package_name};\n"
        f"\t\t}};\n"
    )
    if "/* Begin XCSwiftPackageProductDependency section */" in content:
        content = re.sub(r"(/\* Begin XCSwiftPackageProductDependency section \*/\r?\n)", rf"\1{prod_dep_entry}", content)
    else:
        new_section = (
            "/* Begin XCSwiftPackageProductDependency section */\n"
            + prod_dep_entry +
            "/* End XCSwiftPackageProductDependency section */\n\n"
        )
        if "/* Begin XCBuildConfiguration section */" in content:
            content = re.sub(r"(/\* Begin XCBuildConfiguration section \*/)", rf"{new_section}\1", content)
        else:
            print("Error: Could not locate XCBuildConfiguration section.")
            return False

    # 4. Target packageProductDependencies
    native_target_pattern = r"(/\* Begin PBXNativeTarget section \*/.*?/\* End PBXNativeTarget section \*/)"
    match = re.search(native_target_pattern, content, re.DOTALL)
    if not match:
        print("Error: PBXNativeTarget section not found.")
        return False
    
    native_target_section = match.group(1)
    target_pattern = rf"(\t\t[A-F0-9]{{24}} /\* {target_name} \*/ = \{{.*?name = {target_name};.*?\t\t\}};)"
    target_match = re.search(target_pattern, native_target_section, re.DOTALL)
    if not target_match:
        print(f"Error: Target '{target_name}' block not found in PBXNativeTarget section.")
        return False
    
    target_block = target_match.group(1)
    
    if "packageProductDependencies = (" in target_block:
        new_target_block = re.sub(
            r"(packageProductDependencies = \(\r?\n)",
            rf"\1\t\t\t\t{product_dep_uuid} /* {package_name} */,\n",
            target_block
        )
    else:
        new_target_block = re.sub(
            rf"(productName = {target_name};)",
            rf"packageProductDependencies = (\n\t\t\t\t{product_dep_uuid} /* {package_name} */,\n\t\t\t);\n\t\t\t\1",
            target_block
        )
    content = content.replace(target_block, new_target_block)

    # 5. Project packageReferences
    project_pattern = r"(/\* Begin PBXProject section \*/.*?/\* End PBXProject section \*/)"
    project_match = re.search(project_pattern, content, re.DOTALL)
    if not project_match:
        print("Error: PBXProject section not found.")
        return False
    
    project_section = project_match.group(1)
    if "packageReferences = (" in project_section:
        new_project_section = re.sub(
            r"(packageReferences = \(\r?\n)",
            rf"\1\t\t\t\t{package_ref_uuid} /* RemoteSwiftPackageReference \"{package_name}\" */,\n",
            project_section
        )
    else:
        new_project_section = re.sub(
            r"(productRefGroup = )",
            rf"packageReferences = (\n\t\t\t\t{package_ref_uuid} /* RemoteSwiftPackageReference \"{package_name}\" */,\n\t\t\t);\n\t\t\t\1",
            project_section
        )
    content = content.replace(project_section, new_project_section)

    # 6. Target Frameworks build phase
    fw_phase_match = re.search(r"([A-F0-9]{24}) /\* Frameworks \*/", target_block)
    if not fw_phase_match:
        print("Error: Target does not contain a 'Frameworks' build phase reference.")
        return False
    
    fw_phase_uuid = fw_phase_match.group(1)
    fw_block_pattern = rf"(\t\t{fw_phase_uuid} /\* Frameworks \*/ = \{{.*?files = \((.*?)\);.*?\t\t\}};)"
    fw_match = re.search(fw_block_pattern, content, re.DOTALL)
    if not fw_match:
        print("Error: Frameworks build phase block not found.")
        return False
    
    fw_block = fw_match.group(1)
    fw_files_content = fw_match.group(2)
    
    if "\n" in fw_files_content:
        new_fw_block = re.sub(
            r"(files = \(\r?\n)",
            rf"\1\t\t\t\t{build_file_uuid} /* {package_name} in Frameworks */,\n",
            fw_block
        )
    else:
        new_fw_block = fw_block.replace(
            "files = ();",
            f"files = (\n\t\t\t\t{build_file_uuid} /* {package_name} in Frameworks */,\n\t\t\t);"
        )
    content = content.replace(fw_block, new_fw_block)

    # Write modified content back safely
    with open(pbxproj_path, 'w', encoding='utf-8') as f:
        f.write(content)

    print(f"Success! Added Swift package '{package_name}' ({version}) to target '{target_name}'.")
    return True

if __name__ == "__main__":
    if len(sys.argv) < 6:
        print("Usage: python add_package.py <pbxproj_path> <target_name> <package_name> <repo_url> <minimum_version>")
        print("Example:")
        print("  python add_package.py Tempro.xcodeproj/project.pbxproj Tempro Alamofire https://github.com/Alamofire/Alamofire.git 5.9.0")
        sys.exit(1)
    
    pbxproj_path = sys.argv[1]
    target_name = sys.argv[2]
    package_name = sys.argv[3]
    repo_url = sys.argv[4]
    version = sys.argv[5]
    
    add_package(pbxproj_path, target_name, package_name, repo_url, version)

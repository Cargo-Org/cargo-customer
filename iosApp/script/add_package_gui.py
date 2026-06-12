import sys
import os
import re
import json
import subprocess
import threading
import customtkinter as ctk
from tkinter import filedialog, messagebox
from packaging.version import parse as parse_version

# Configuration constants
CONFIG_FILE = ".script_preference.json"

# Set up CustomTkinter style
ctk.set_appearance_mode("System")  # Options: "System", "Dark", "Light"
ctk.set_default_color_theme("blue")  # Options: "blue", "green", "dark-blue"

def load_config():
    if os.path.exists(CONFIG_FILE):
        try:
            with open(CONFIG_FILE, 'r', encoding='utf-8') as f:
                return json.load(f)
        except Exception:
            pass
    return {}

def save_config(pbxproj_path, target_name):
    config = {
        "pbxproj_path": pbxproj_path,
        "target_name": target_name
    }
    try:
        with open(CONFIG_FILE, 'w', encoding='utf-8') as f:
            json.dump(config, f, indent=4)
    except Exception as e:
        print(f"Error saving config: {e}")

class TemproPackageGUI(ctk.CTk):
    def __init__(self):
        super().__init__()

        self.title("Tempro - Swift Package Manager Integrator")
        self.geometry("760x700")
        self.minsize(700, 650)
        
        # Load configuration
        self.config = load_config()

        # Grid configuration
        self.grid_columnconfigure(0, weight=1)
        self.grid_rowconfigure(0, weight=0) # Title
        self.grid_rowconfigure(1, weight=0) # Config frame
        self.grid_rowconfigure(2, weight=0) # Package frame
        self.grid_rowconfigure(3, weight=1) # Log console frame
        self.grid_rowconfigure(4, weight=0) # Footer

        self.create_widgets()
        self.apply_loaded_config()

    def create_widgets(self):
        # ---------------- Title Section ----------------
        title_frame = ctk.CTkFrame(self, fg_color="transparent")
        title_frame.grid(row=0, column=0, padx=20, pady=(20, 10), sticky="ew")
        
        title_label = ctk.CTkLabel(
            title_frame, 
            text="Tempro Package Integrator", 
            font=ctk.CTkFont(family="Segoe UI", size=26, weight="bold")
        )
        title_label.pack(anchor="w")
        
        subtitle_label = ctk.CTkLabel(
            title_frame, 
            text="Add Swift Packages to your Xcode project files (.pbxproj) from Windows", 
            font=ctk.CTkFont(family="Segoe UI", size=13),
            text_color="gray"
        )
        subtitle_label.pack(anchor="w", pady=(2, 0))

        # ---------------- Workspace Configuration Frame ----------------
        config_frame = ctk.CTkFrame(self)
        config_frame.grid(row=1, column=0, padx=20, pady=10, sticky="ew")
        config_frame.grid_columnconfigure(1, weight=1)
        
        config_title = ctk.CTkLabel(
            config_frame, 
            text="Workspace Configuration (Saved)", 
            font=ctk.CTkFont(family="Segoe UI", size=14, weight="bold"),
            text_color="#3B8ED0"
        )
        config_title.grid(row=0, column=0, columnspan=3, padx=15, pady=(10, 5), sticky="w")

        # pbxproj path
        pbx_label = ctk.CTkLabel(config_frame, text="Xcode project.pbxproj Path:", font=ctk.CTkFont(size=12))
        pbx_label.grid(row=1, column=0, padx=(15, 10), pady=8, sticky="w")
        
        self.pbxproj_entry = ctk.CTkEntry(
            config_frame, 
            placeholder_text="D:/path/to/YourProject.xcodeproj/project.pbxproj"
        )
        self.pbxproj_entry.grid(row=1, column=1, padx=5, pady=8, sticky="ew")
        
        browse_btn = ctk.CTkButton(
            config_frame, 
            text="Browse...", 
            width=100, 
            command=self.browse_pbxproj
        )
        browse_btn.grid(row=1, column=2, padx=(5, 15), pady=8)

        # target name
        target_label = ctk.CTkLabel(config_frame, text="Target Xcode Name:", font=ctk.CTkFont(size=12))
        target_label.grid(row=2, column=0, padx=(15, 10), pady=(8, 15), sticky="w")
        
        self.target_entry = ctk.CTkEntry(config_frame, placeholder_text="e.g. Tempro, iosApp")
        self.target_entry.grid(row=2, column=1, columnspan=2, padx=(5, 15), pady=(8, 15), sticky="ew")

        # ---------------- Swift Package Details Frame ----------------
        pkg_frame = ctk.CTkFrame(self)
        pkg_frame.grid(row=2, column=0, padx=20, pady=10, sticky="ew")
        pkg_frame.grid_columnconfigure(1, weight=1)

        pkg_title = ctk.CTkLabel(
            pkg_frame, 
            text="Swift Package Details", 
            font=ctk.CTkFont(family="Segoe UI", size=14, weight="bold"),
            text_color="#3B8ED0"
        )
        pkg_title.grid(row=0, column=0, columnspan=3, padx=15, pady=(10, 5), sticky="w")

        # repo url
        url_label = ctk.CTkLabel(pkg_frame, text="Repository URL:", font=ctk.CTkFont(size=12))
        url_label.grid(row=1, column=0, padx=(15, 10), pady=8, sticky="w")
        
        self.url_entry = ctk.CTkEntry(
            pkg_frame, 
            placeholder_text="e.g. https://github.com/Alamofire/Alamofire.git"
        )
        self.url_entry.grid(row=1, column=1, padx=5, pady=8, sticky="ew")
        self.url_entry.bind("<FocusOut>", self.on_url_focus_out)
        self.url_entry.bind("<KeyRelease>", self.on_url_key_release)
        
        self.fetch_btn = ctk.CTkButton(
            pkg_frame, 
            text="Fetch Versions", 
            width=120, 
            command=self.start_fetch_versions
        )
        self.fetch_btn.grid(row=1, column=2, padx=(5, 15), pady=8)

        # package name
        name_label = ctk.CTkLabel(pkg_frame, text="Package Name:", font=ctk.CTkFont(size=12))
        name_label.grid(row=2, column=0, padx=(15, 10), pady=8, sticky="w")
        
        self.name_entry = ctk.CTkEntry(pkg_frame, placeholder_text="e.g. Alamofire (extracted automatically)")
        self.name_entry.grid(row=2, column=1, columnspan=2, padx=(5, 15), pady=8, sticky="ew")

        # version select
        ver_label = ctk.CTkLabel(pkg_frame, text="Package Version:", font=ctk.CTkFont(size=12))
        ver_label.grid(row=3, column=0, padx=(15, 10), pady=(8, 15), sticky="w")
        
        self.version_combo = ctk.CTkComboBox(
            pkg_frame, 
            values=["Enter repository URL and click Fetch Versions"],
            state="readonly"
        )
        self.version_combo.grid(row=3, column=1, columnspan=2, padx=(5, 15), pady=(8, 15), sticky="ew")

        # ---------------- Console Logs Section ----------------
        console_frame = ctk.CTkFrame(self)
        console_frame.grid(row=3, column=0, padx=20, pady=10, sticky="nsew")
        console_frame.grid_columnconfigure(0, weight=1)
        console_frame.grid_rowconfigure(1, weight=1)

        console_title = ctk.CTkLabel(
            console_frame, 
            text="Console & Integration Log:", 
            font=ctk.CTkFont(family="Segoe UI", size=13, weight="bold")
        )
        console_title.grid(row=0, column=0, padx=15, pady=(8, 2), sticky="w")

        self.console_textbox = ctk.CTkTextbox(
            console_frame, 
            font=ctk.CTkFont(family="Consolas", size=11),
            fg_color="#121212" if ctk.get_appearance_mode() == "Dark" else "#F0F0F0",
            text_color="#E0E0E0" if ctk.get_appearance_mode() == "Dark" else "#1A1A1A"
        )
        self.console_textbox.grid(row=1, column=0, padx=15, pady=(2, 10), sticky="nsew")
        self.console_textbox.configure(state="disabled")

        # ---------------- Integration & Footer Section ----------------
        footer_frame = ctk.CTkFrame(self, fg_color="transparent")
        footer_frame.grid(row=4, column=0, padx=20, pady=(5, 20), sticky="ew")
        
        self.theme_menu = ctk.CTkOptionMenu(
            footer_frame, 
            values=["System", "Dark", "Light"],
            command=self.change_theme,
            width=100
        )
        self.theme_menu.pack(side="left", padx=(5, 0))
        self.theme_menu.set("System")

        self.integrate_btn = ctk.CTkButton(
            footer_frame, 
            text="Add Package to Xcode Project", 
            font=ctk.CTkFont(size=14, weight="bold"),
            height=40,
            command=self.run_integration
        )
        self.integrate_btn.pack(side="right", fill="x", expand=True, padx=(20, 5))

    def apply_loaded_config(self):
        pbx = self.config.get("pbxproj_path", "")
        target = self.config.get("target_name", "")
        
        if pbx:
            self.pbxproj_entry.insert(0, pbx)
        if target:
            self.target_entry.insert(0, target)

    def browse_pbxproj(self):
        file_path = filedialog.askopenfilename(
            title="Select project.pbxproj",
            filetypes=[("Xcode Project Configurations", "project.pbxproj"), ("All Files", "*.*")]
        )
        if file_path:
            self.pbxproj_entry.delete(0, 'end')
            self.pbxproj_entry.insert(0, file_path)

    def change_theme(self, new_appearance_mode):
        ctk.set_appearance_mode(new_appearance_mode)
        # Update console background color matching the theme
        if new_appearance_mode == "Dark" or (new_appearance_mode == "System" and ctk.get_appearance_mode() == "Dark"):
            self.console_textbox.configure(fg_color="#121212", text_color="#E0E0E0")
        else:
            self.console_textbox.configure(fg_color="#F0F0F0", text_color="#1A1A1A")

    def log_message(self, message):
        self.console_textbox.configure(state="normal")
        self.console_textbox.insert("end", message + "\n")
        self.console_textbox.see("end")
        self.console_textbox.configure(state="disabled")

    def clear_logs(self):
        self.console_textbox.configure(state="normal")
        self.console_textbox.delete("1.0", "end")
        self.console_textbox.configure(state="disabled")

    def extract_package_name_from_url(self):
        url = self.url_entry.get().strip()
        if not url:
            return
        
        # Clean url
        if url.endswith(".git"):
            url = url[:-4]
        url = url.rstrip('/')
        
        parts = url.split('/')
        if parts:
            package_name = parts[-1]
            # If package name is empty or doesn't look like a standard identifier, ignore
            if package_name and package_name != "github.com":
                current_name = self.name_entry.get().strip()
                if not current_name:
                    self.name_entry.delete(0, 'end')
                    self.name_entry.insert(0, package_name)

    def on_url_focus_out(self, event):
        self.extract_package_name_from_url()

    def on_url_key_release(self, event):
        # Trigger basic parsing if URL matches a repo format
        self.extract_package_name_from_url()

    def start_fetch_versions(self):
        url = self.url_entry.get().strip()
        if not url:
            messagebox.showerror("Validation Error", "Please enter a valid Git Repository URL.")
            return

        self.extract_package_name_from_url()
        self.log_message(f"Checking git repository for tags at '{url}'...")
        self.fetch_btn.configure(state="disabled", text="Fetching...")
        
        def callback(versions, error):
            # Run GUI updates safely in the main thread
            self.after(0, self.finish_fetch_versions, versions, error)

        def worker():
            try:
                # Use subprocess to run git ls-remote --tags
                result = subprocess.run(
                    ['git', 'ls-remote', '--tags', url],
                    capture_output=True, text=True, check=True, timeout=12
                )
                
                tags = []
                for line in result.stdout.strip().split('\n'):
                    if not line:
                        continue
                    parts = line.split('\t')
                    if len(parts) < 2:
                        continue
                    ref = parts[1]
                    if ref.startswith('refs/tags/'):
                        tag = ref[len('refs/tags/'):]
                        if tag.endswith('^{}'):
                            tag = tag[:-3]
                        if tag not in tags:
                            tags.append(tag)
                
                # Filter tags by semantic version pattern (X.Y.Z or vX.Y.Z)
                semver_pat = re.compile(r'^v?(\d+\.\d+(?:\.\d+)?(?:[-+][a-zA-Z0-9.]+)?)$')
                valid_versions = []
                for tag in tags:
                    if semver_pat.match(tag):
                        valid_versions.append(tag)
                
                def sort_key(t):
                    clean_tag = t[1:] if t.startswith('v') else t
                    try:
                        return parse_version(clean_tag)
                    except Exception:
                        return parse_version("0.0.0")
                
                sorted_versions = sorted(valid_versions, key=sort_key, reverse=True)
                
                # Fallback: if no semver tags found but other tags exist
                if not sorted_versions and tags:
                    sorted_versions = sorted(tags, reverse=True)
                    
                if not sorted_versions:
                    callback([], "No tags/releases found in the repository.")
                else:
                    callback(sorted_versions, None)
                    
            except subprocess.TimeoutExpired:
                callback([], "Git request timed out. Please check your internet connection or repository URL.")
            except subprocess.CalledProcessError as e:
                err_msg = e.stderr.strip()
                if "not found" in err_msg.lower() or "repository" in err_msg.lower():
                    callback([], f"Repository not found. Ensure the URL is public and correct.\nGit error: {err_msg}")
                else:
                    callback([], f"Failed to fetch tags.\nGit error: {err_msg}")
            except Exception as e:
                callback([], f"An error occurred: {str(e)}")

        threading.Thread(target=worker, daemon=True).start()

    def finish_fetch_versions(self, versions, error):
        self.fetch_btn.configure(state="normal", text="Fetch Versions")
        if error:
            self.log_message(f"❌ Error: {error}")
            self.version_combo.configure(values=["No versions fetched"])
            self.version_combo.set("No versions fetched")
            messagebox.showerror("Fetch Failed", error)
        else:
            self.log_message(f"✅ Successfully fetched {len(versions)} versions.")
            self.version_combo.configure(values=versions, state="readonly")
            self.version_combo.set(versions[0]) # Default to the latest version

    def run_integration(self):
        # Input validation
        pbxproj = self.pbxproj_entry.get().strip()
        target = self.target_entry.get().strip()
        repo_url = self.url_entry.get().strip()
        package_name = self.name_entry.get().strip()
        version = self.version_combo.get().strip()

        if not pbxproj or not target or not repo_url or not package_name or not version:
            messagebox.showerror("Validation Error", "All fields must be filled out to integrate a package.")
            return

        if version in ["Enter repository URL and click Fetch Versions", "No versions fetched", ""]:
            messagebox.showerror("Validation Error", "Please fetch and select a valid release version first.")
            return

        # Double check file exists
        if not os.path.exists(pbxproj):
            messagebox.showerror("File Error", f"The project.pbxproj file was not found at:\n{pbxproj}")
            return

        # Save config for next session
        save_config(pbxproj, target)

        self.clear_logs()
        self.log_message("🚀 Starting Swift Package Integration...")
        self.log_message(f"Target Project: {pbxproj}")
        self.log_message(f"Target Name:    {target}")
        self.log_message(f"Package Name:   {package_name}")
        self.log_message(f"Repository URL: {repo_url}")
        self.log_message(f"Version Rule:   Up to Next Major ({version})")
        self.log_message("-" * 50)

        self.integrate_btn.configure(state="disabled", text="Integrating...")

        def worker():
            try:
                # Invoke the original script via subprocess to bypass sys.exit issues and run cleanly
                # We use sys.executable to run with the exact same python context
                # Arguments: python add_package.py <pbxproj_path> <target_name> <package_name> <repo_url> <minimum_version>
                cmd = [
                    sys.executable,
                    "add_package.py",
                    pbxproj,
                    target,
                    package_name,
                    repo_url,
                    version
                ]
                
                process = subprocess.Popen(
                    cmd,
                    stdout=subprocess.PIPE,
                    stderr=subprocess.PIPE,
                    text=True,
                    bufsize=1
                )
                
                # Read output in real time
                while True:
                    output = process.stdout.readline()
                    if output == '' and process.poll() is not None:
                        break
                    if output:
                        self.after(0, self.log_message, output.strip())
                
                rc = process.poll()
                stderr_output = process.stderr.read()
                
                if stderr_output:
                    self.after(0, self.log_message, f"Stderr:\n{stderr_output.strip()}")
                
                if rc == 0:
                    self.after(0, self.log_message, "\n🎉 Integration completed successfully!")
                    self.after(0, lambda: messagebox.showinfo("Success", f"Swift package '{package_name}' has been added successfully!"))
                else:
                    self.after(0, self.log_message, f"\n❌ Integration failed with exit code {rc}")
                    self.after(0, lambda: messagebox.showerror("Integration Failed", "Check the log console for error details."))

            except Exception as e:
                self.after(0, self.log_message, f"\n❌ System error: {str(e)}")
                self.after(0, lambda: messagebox.showerror("Error", f"Failed to run integration: {str(e)}"))
            finally:
                self.after(0, lambda: self.integrate_btn.configure(state="normal", text="Add Package to Xcode Project"))

        threading.Thread(target=worker, daemon=True).start()

if __name__ == "__main__":
    app = TemproPackageGUI()
    app.mainloop()

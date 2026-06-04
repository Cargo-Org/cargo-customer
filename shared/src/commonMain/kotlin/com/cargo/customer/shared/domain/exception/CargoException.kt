package com.cargo.customer.shared.domain.exception

sealed class CargoException(message: String = "") : Exception(message)

class NoInternetException : CargoException("No internet connection")
class UnauthorizedException : CargoException("Unauthorized")
class NotFoundException : CargoException("Not found")
class ServerException : CargoException("Server error")
class UnknownException : CargoException("Unknown error")
class UploadUrlException : CargoException("Upload url error")
class UploadImageException : CargoException("Upload image error")
class GetDocumentsException : CargoException("get documents error")
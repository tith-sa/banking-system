package banking.system.exception

open class HandleException (val status: String, message: String): Exception(message)

class BadRequestException(message: String): HandleException(StatusException.BAD_REQUEST, message)
class NotFoundException(message: String): HandleException(StatusException.NOT_FOUND, message)
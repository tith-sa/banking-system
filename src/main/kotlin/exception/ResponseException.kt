package banking.system.exception


fun <T> T.toSuccess( message : String): Response<T> {
    return Response( true,  this, message)
}

fun Throwable.toFailure( message : String): Response<Nothing?> {
    return Response( false, null, message)
}
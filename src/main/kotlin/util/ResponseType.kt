package banking.system.util

import banking.system.dto.Response
import banking.system.exception.HandleException
import banking.system.exception.StatusException


fun <T> T.toSuccess(status: String = StatusException.OK, message : String): Response<T> {
    return Response(true, status,this, message)
}

fun HandleException.toFailure( message : String): Response<Nothing> {
    return Response(false, status,null, message)
}
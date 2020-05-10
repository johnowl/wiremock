package com.johnowl.mock.wiremock.server

import com.github.tomakehurst.wiremock.extension.requestfilter.AdminRequestFilter
import com.github.tomakehurst.wiremock.extension.requestfilter.RequestFilterAction
import com.github.tomakehurst.wiremock.http.Request
import com.github.tomakehurst.wiremock.http.ResponseDefinition
import java.lang.IllegalArgumentException

class AdminApiAuthorizer: AdminRequestFilter() {

    override fun getName() = "admin-api-authorizer"

    override fun filter(request: Request?): RequestFilterAction {
        request ?: throw IllegalArgumentException("request")

        return if (request.getHeader("Authorization") == System.getenv("ADMIN_BEARER_TOKEN")) {
            RequestFilterAction.continueWith(request)
        } else {
            println("Tentativa de acesso ao portal admin sem envio de credenciais.")
            RequestFilterAction.stopWith(ResponseDefinition.notPermitted("Acesso não permitido."))
        }
    }
}
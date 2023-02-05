package com.example.url_shortener.service


import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class ReplicationService {
    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun replicateAcrossAllNodes(longUrl: String, nodeList: Array<String>): String {
        var shortUrl: String = "";
        for (nodeAddress in nodeList) {

            val client = HttpClient(CIO)
            val response: HttpResponse = client.get(nodeAddress) {
                url {
                    parameters.append("url", longUrl)
                }
            }
            client.close()
            logger.info("is SENDING to $nodeAddress, result=$response.status.toString()")
            shortUrl = response.bodyAsText()
        }
        return shortUrl
    }
}
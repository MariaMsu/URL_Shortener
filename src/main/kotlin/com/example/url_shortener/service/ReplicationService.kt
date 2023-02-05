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

    suspend fun replicateAcrossAllNodes(longUrl: String, nodeList: Array<String>) {
        for (nodeAddress in nodeList) {
            logger.info("is SENDING to $nodeAddress");

            val client = HttpClient(CIO)
            val response: HttpResponse = client.get(nodeAddress)
            client.close()

            logger.info(response.status.toString());
        }

    }
}
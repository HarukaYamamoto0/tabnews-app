package com.harukadev.tabnews.posts.domain

import kotlinx.serialization.Serializable

@Serializable
data class PostContent(
    val type: String,
    val id: String,
    val ownerId: String,
    val parentId: String? = null,
    val ownerUsername: String,
    val slug: String,
    val title: String,
    val body: String,
    val comments: Int,
    val sourceUrl: String? = null,
    val status: String,
    val tabcoins: Int,
    val tabcoinsCredit: Int,
    val tabcoinsDebit: Int,
    val createdAt: String,
    val deletedAt: String? = null,
    val publishedAt: String,
    val updatedAt: String
)

 internal val fakePost = PostContent(
    type = "content",
    id = "c6ae369c-2080-43ca-9c36-2c8773ad0309",
    ownerId = "ae7444e1-2881-453a-8c39-21d2b0fc5e79",
    parentId = null,
    ownerUsername = "admin",
    slug = "duvida-como-continuar-estudando-e-desafiando-minha-mente",
    title = "DUVIDA: como continuar estudando e desafiando minha mente?",
    body = """Fala galera!

Sou dev há 10 anos e umas das coisas que mais gosto nesse mundo é como programar desafia a mente.

A sensação de sentir meus neurônios vibrando e criando novas conexões ao aprender algo novo é deliciosa e viciante, o problema que vivo agora e peço a ajuda de vocês é: como viver isso de forma recorrente?

Como? Onde? De que jeito estudar e continuar aprendendo conceitos complexos e de forma correta, metodológica e direcionada?

Sinto que hoje na mesma medida que temos acesso a informação de forma democrática, também é muito mais difícil tracejar uma rota de aprendizado profundo.

Sofro com querer aprender matemática, por exemplo, com mais profundidade e conceitos, mas pensar que precisaria fazer uma outra graduação ou lidar com um UDEMY super basiquinho da vida pra isso.

Não quero uma nova carreira, nem nada do tipo, afinal já trabalho bastante.

Quero só continuar treinando e capacitando a minha inteligência (coisa que nem via ChatGPT consigo satisfatoriamente fazer).

E aí? O que me dizem? Como têm feito por aí?""",
    comments = 0,
    sourceUrl = null,
    status = "published",
    tabcoins = 1,
    tabcoinsCredit = 0,
    tabcoinsDebit = 0,
    createdAt = "2025-01-08T09:46:11.572Z",
    deletedAt = null,
    publishedAt = "2025-01-08T09:46:11.594Z",
    updatedAt = "2025-01-08T09:46:11.572Z"
)

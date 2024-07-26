package com.harukadev.tabnews.data

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
data class Post(
    val id: String,
    val slug: String,
    val title: String,
    val body: String? = fakeData[0].body,
    @SerialName("owner_id")
    val ownerId: String,
    @SerialName("parent_id")
    val parentId: String?,
    val status: String,
    @SerialName("source_url")
    val sourceUrl: String?,
    @SerialName("created_at")
    var createdAtRaw: String,
    @SerialName("updated_at")
    val updatedAtRaw: String,
    @SerialName("published_at")
    val publishedAtRaw: String,
    @SerialName("deleted_at")
    val deletedAtRaw: String?,
    val tabcoins: Int,
    @SerialName("tabcoins_credit")
    val tabcoinsCredit: Int,
    @SerialName("tabcoins_debit")
    val tabcoinsDebit: Int,
    @SerialName("owner_username")
    val author: String,
    @SerialName("children_deep_count")
    val totalComments: Int,
    val type: String
) {
    private fun formatDate(dateString: String): String {
        val instant = Instant.parse(dateString)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        val javaDateTime = dateTime.toJavaLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return javaDateTime.format(formatter)
    }

    val createdAt: String
        get() = formatDate(createdAtRaw)

    val updatedAt: String
        get() = formatDate(updatedAtRaw)

    val publishedAt: String
        get() = formatDate(publishedAtRaw)

    val deletedAt: String? = if (deletedAtRaw != null) formatDate(deletedAtRaw) else null
}

val fakeData: Array<Post> = arrayOf(
    Post(
        id = "d5b4e195-9f2e-47bb-b097-8a51dfc0886e",
        slug = "dev-na-gringa-contratacao-impostos-hardware-e-comecando-do-zero",
        title = "Dev na Gringa: contratação, impostos, hardware e começando do zero.",
        totalComments = 6,
        ownerId = "d0d22c94-249a-4dba-bfbe-305237ea78d1",
        parentId = null,
        status = "published",
        sourceUrl = "https://devnagringa.substack.com/p/dev-na-gringa-contratacao-impostos-hardware",
        createdAtRaw = "2024-07-22T02:24:28.244Z",
        updatedAtRaw = "2024-07-22T02:29:27.739Z",
        publishedAtRaw = "2024-07-22T02:24:28.255Z",
        deletedAtRaw = null,
        tabcoins = 19,
        tabcoinsCredit = 21,
        tabcoinsDebit = -3,
        author = "lucasfaria",
        type = "content",
        body = "post 1"
    ),
    Post(
        id = "424b1e31-78af-4516-8854-0feab9dc1491",
        slug = "travada-muito-besta-em-uma-entrevista-me-diz-o-que-aconteceria-com-voce",
        title = "TRAVEI NA ENTREVISTA: O próximo pode ser você CUIDADO!!!!",
        totalComments = 7,
        ownerId = "917aca18-f954-4da5-a809-ba30a98ba7c8",
        parentId = null,
        status = "published",
        sourceUrl = null,
        createdAtRaw = "2024-07-21T22:26:14.116Z",
        updatedAtRaw = "2024-07-21T23:19:42.785Z",
        publishedAtRaw = "2024-07-21T22:26:14.119Z",
        deletedAtRaw = null,
        tabcoins = 12,
        tabcoinsCredit = 11,
        tabcoinsDebit = 0,
        author = "juninhopo",
        type = "content",
        body = "post 2"
    ),
    Post(
        id = "90211adf-4dfe-4ba5-828b-094332a3bb80",
        slug = "pitch-que-tal-aprender-melhorar-seu-ingles-usando-videos-do-youtube",
        title = "PITCH | QUE TAL APRENDER/MELHORAR SEU INGLÊS USANDO VÍDEOS DO YOUTUBE?",
        totalComments = 3,
        ownerId = "ff01434a-080a-4561-96ab-61cfd0e9f635",
        parentId = null,
        status = "published",
        sourceUrl = null,
        createdAtRaw = "2024-07-22T02:07:27.781Z",
        updatedAtRaw = "2024-07-22T02:12:41.708Z",
        publishedAtRaw = "2024-07-22T02:07:27.809Z",
        deletedAtRaw = null,
        tabcoins = 7,
        tabcoinsCredit = 6,
        tabcoinsDebit = 0,
        author = "gustavomtn",
        type = "content",
        body = "post 3"
    ),
    Post(
        id = "750f18b3-c258-47cd-9e08-bb420ef6a907",
        slug = "self-hosting-um-guia-para-iniciantes-hospede-voce-mesmo-em-sua-casa",
        title = "Self Hosting - Um guia para iniciantes - hospede você mesmo em sua casa!",
        totalComments = 3,
        ownerId = "f26f617a-53b2-419c-b3c7-104395d59395",
        parentId = null,
        status = "published",
        sourceUrl = "https://ente.io/blog/self-hosting-101/",
        createdAtRaw = "2024-07-22T13:04:00.810Z",
        updatedAtRaw = "2024-07-22T13:04:00.810Z",
        publishedAtRaw = "2024-07-22T13:04:00.824Z",
        deletedAtRaw = null,
        tabcoins = 6,
        tabcoinsCredit = 5,
        tabcoinsDebit = 0,
        author = "uriel",
        type = "content",
        body = "post 4"
    ),
    Post(
        id = "ddf4e77b-4a86-4c11-b8de-b50c53817e03",
        slug = "crowdstrike-analise-do-erro",
        title = "CrowdStrike - Análise do Erro",
        totalComments = 0,
        ownerId = "483422ae-e5e7-4217-a476-f6c5f4a722b3",
        parentId = null,
        status = "published",
        sourceUrl = null,
        createdAtRaw = "2024-07-22T22:50:52.578Z",
        updatedAtRaw = "2024-07-22T22:51:20.428Z",
        publishedAtRaw = "2024-07-22T22:50:52.578Z",
        deletedAtRaw = null,
        tabcoins = 5,
        tabcoinsCredit = 7,
        tabcoinsDebit = -3,
        author = "gsampaio",
        type = "content",
        body = "post 5"
    ),
    Post(
        id = "f7de8ddc-3068-4b69-8dae-eb80baedac9b",
        slug = "estruturas-de-controle-em-python",
        title = "Estruturas de Controle em Python",
        totalComments = 0,
        ownerId = "8272f0bb-95ca-4eb2-940f-80af31ac5a6c",
        parentId = null,
        status = "published",
        sourceUrl = null,
        createdAtRaw = "2024-07-22T18:17:35.779Z",
        updatedAtRaw = "2024-07-22T18:17:35.779Z",
        publishedAtRaw = "2024-07-22T18:17:35.803Z",
        deletedAtRaw = null,
        tabcoins = 5,
        tabcoinsCredit = 4,
        tabcoinsDebit = 0,
        author = "nicolas11",
        type = "content",
        body = "post 6"
    )
)
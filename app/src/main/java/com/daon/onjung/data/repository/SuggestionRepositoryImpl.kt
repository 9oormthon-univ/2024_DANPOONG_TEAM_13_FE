package com.daon.onjung.data.repository

import android.content.Context
import android.net.Uri
import com.daon.onjung.data.datasource.SuggestionDataSource
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.PostCommentRequest
import com.daon.onjung.network.model.response.PostBoardResponse
import com.daon.onjung.util.fileFromContentUri
import com.daon.onjung.util.resizeAndSaveImage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class SuggestionRepositoryImpl @Inject constructor(
    private val suggestionDataSource: SuggestionDataSource,
    @ApplicationContext private val context: Context
) : SuggestionRepository {

    override suspend fun postBoard(
        imageUri: Uri?,
        title: String,
        content: String
    ): Flow<ApiResult<BaseResponse<PostBoardResponse>>> {
        val jsonObjectBuilder = JSONObject().apply {
            put("title", title)
            put("content", content)
        }

        val body = jsonObjectBuilder.toString().toRequestBody("application/json".toMediaType())

        val imageFile: MultipartBody.Part? = imageUri?.let { uri ->
            val originalFile = fileFromContentUri(context, uri)
            val compressedFile = resizeAndSaveImage(context, originalFile)

            val requestBody = compressedFile.asRequestBody("image/*".toMediaType())
            MultipartBody.Part.createFormData("file", compressedFile.name, requestBody)
        }

        return suggestionDataSource.postBoard(body, imageFile)
    }

    override suspend fun getBoardDetail(
        id: Int
    ) = suggestionDataSource.getBoardDetail(id)

    override suspend fun putLikeBoard(
        id: Int
    ) = suggestionDataSource.putLikeBoard(id)

    override suspend fun postComment(
        id: Int,
        content: String
    ) = suggestionDataSource.postComment(id, PostCommentRequest(content))

    override suspend fun getCommentList(
        id: Int,
        page: Int,
        size: Int
    ) = suggestionDataSource.getCommentList(id, page, size)
}
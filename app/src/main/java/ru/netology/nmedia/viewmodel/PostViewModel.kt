package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.model.FeedModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryImpl
import ru.netology.nmedia.utils.SingleLiveEvent
import kotlinx.coroutines.launch


private val emptyPost = Post(
    id = 0,
    author = "",
    authorAvatar = "",
    content = "",
    published = "",
    likes = 0,
    likedByMe = false,
    share = false,
    sharesCount = 0
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryImpl()
    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data
    val edited = MutableLiveData(emptyPost)
    private val _postCreated = SingleLiveEvent<Unit>()
    val postCreated: LiveData<Unit>
        get() = _postCreated
    val old = _data.value?.posts.orEmpty()
    val avatars = emptyList<String>()

    init {
        loadPosts()
    }

    fun loadPosts() {
        _data.value = FeedModel(loading = true)
        viewModelScope.launch {
            val posts = repository.getAll()
            _data.postValue(FeedModel(posts = posts, empty = posts.isEmpty()))
        }
    }

    fun save() {
        edited.value?.let {
//            repository.save(it, object : PostRepository.PostCallback {
//                override fun onSuccess(post: Post) {
//                    _postCreated.postValue(Unit)
////                    repository.saveDraft(null) //на сервере не реализован черновик
//                }
//
//                override fun onError(e: Exception) {
//                    _data.postValue(_data.value?.copy(posts = old))
//                }
//            })
//        }
//        edited.value = emptyPost
    }

    fun changeContent(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content != text)
                edited.value = it.copy(content = text)
        }
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun likeById(id: Long) {
//        repository.likedById(id, object : PostRepository.PostCallback {
//            override fun onSuccess(post: Post) {
//                _data.postValue(
//                    _data.value?.copy(posts = _data.value?.posts.orEmpty()
//                        .map {
//                            if (it.id == id) post else it
//                        }
//                    )
//                )
//            }
//
//            override fun onError(e: Exception) {
//                _data.postValue(_data.value?.copy(posts = old))
//            }
//        })
    }


    fun unlikeById(id: Long) {
//        repository.unlikeById(id, object : PostRepository.PostCallback {
//            override fun onSuccess(post: Post) {
//                _data.postValue(
//                    _data.value?.copy(posts = _data.value?.posts.orEmpty()
//                        .map {
//                            if (it.id == id) post else it
//                        }
//                    )
//                )
//            }
//
//            override fun onError(e: Exception) {
//                _data.postValue(_data.value?.copy(posts = old))
//            }
//        })
    }

    fun shareById(id: Long) = repository.shareById(id)
    fun removeById(id: Long) {
//        repository.removeById(id, object : PostRepository.IdCall {
//            override fun onSuccess(unit: Unit) {
//                _data.postValue(
//                    _data.value?.copy(posts = _data.value?.posts.orEmpty()
//                        .filter { it.id != id }
//                    )
//                )
//            }
//
//            override fun onError(e: Exception) {
//                _data.postValue(_data.value?.copy(posts = old))
//            }
//        })
    }


        fun cancelEditing() = edited.value?.let {
//        repository.cancelEditing(it)
        }

//    fun saveDraft(draft: String?) = repository.saveDraft(draft)
//    fun getDraft(): String? = repository.getDraft()

    }
}
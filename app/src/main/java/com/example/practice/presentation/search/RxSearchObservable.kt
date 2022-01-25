package com.example.practice.presentation.search

import androidx.appcompat.widget.SearchView
import io.reactivex.rxjava3.core.Observable

class RxSearchObservable {
    fun fromView(searchView: SearchView): Observable<String> {
        return Observable.create { subscriber ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText)
                    return false
                }
            })
        }
    }
}

package com.posist.sk.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.posist.sk.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_skdata.*

class SKDataActivity : AppCompatActivity() {

    val skDataViewModel by viewModels<SKDataViewModel>()
    //val recycler = findViewById<RecyclerView>(R.id.rcv_data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skdata)
        setup()
    }


    fun setup(){
        skDataViewModel.getSKData()
        val skAdapter  = SkDataAdapter(this)
        rcv_data.layoutManager = LinearLayoutManager(this)
        rcv_data.adapter = skAdapter
        skDataViewModel.dataList.observe(this, Observer {
            if(it!=null)
                skAdapter.setData(it)
        })
    }


}
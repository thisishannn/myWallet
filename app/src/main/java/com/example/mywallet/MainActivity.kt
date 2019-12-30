package com.example.mywallet

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var inOutLogViewModel: InOutLogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Create RecyclerView
        val recyclerView : RecyclerView = findViewById(R.id.recyclerview)
// Create an adapter
        val adapter = InOutLogAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //Initialize the viewModel

        inOutLogViewModel = ViewModelProvider(this)
            .get(InOutLogViewModel :: class.java)

        inOutLogViewModel.allLogs.observe(
            this,
            Observer{
                if(it.isNotEmpty()){
                    adapter.setInOutLogList(it)
                }
            }
        )


        buttonAdd.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int
                                  , data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ADD) {
            if(resultCode == Activity.RESULT_OK){
                val _amount = data?.getStringExtra(AddActivity.AMOUNT)
                val _type = data?.getIntExtra(AddActivity.TYPE, 1)

                val inOutLog = InOutLog(id = 1,
                    amount = _amount!!.toFloat(),
                    type = _type!!)

                //TODO: INSERT DATA TO THE DB USING DAO
                inOutLogViewModel.insertLog(inOutLog)
            }
        }
    }

    companion object const val  REQUEST_ADD = 1
}

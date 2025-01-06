package ru.yatsevyukcompany.bloodpleasurediary

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class CompanyActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company)


        val i = Intent(this@CompanyActivity, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(i)
            finish()
        }, 1000)
    }
}
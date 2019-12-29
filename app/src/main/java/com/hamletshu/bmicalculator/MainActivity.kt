package com.hamletshu.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import java.util.prefs.PreferencesFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //이전에 입력한 값 읽어오기
        loadData()

        resultButton.setOnClickListener {

            //마지막에 입력한 내용 저장
            saveData(heightEditText.text.toString().toInt(),
                weightEditText.text.toString().toInt())

            /*val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("weight", weightEditText.text.toString())
            intent.putExtra("height", heightEditText.text.toString())
            startActivity(intent)*/
            //anko 라이브러리를 통해 간결하게 사용 가능
            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }

    private fun saveData(height: Int, weight: Int){
        //사용되지 않는 소스라 build.gradle에 implementation "androidx.preference:preference-ktx:1.1.0" 추가함
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit() // Preference에 데이터를 담음

        //키와 값 쌍으로 저장을 함
        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply() // 설정한 내용 반영
    }

    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT",0) // 저장값이 없을 때 기본값은 0을 리턴
        val weight = pref.getInt("KEY_WEIGHT",0) // 저장값이 없을 때 기본값은 0을 리턴

        if(height != 0 && weight != 0){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}

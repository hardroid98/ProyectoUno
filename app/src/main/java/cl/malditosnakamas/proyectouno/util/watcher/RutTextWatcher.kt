package cl.malditosnakamas.proyectouno.util.watcher

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import cl.malditosnakamas.proyectouno.util.formater.RutFormatter

class RutTextWatcher : TextWatcher {

    lateinit var editText: AppCompatEditText

    fun attachEditText(editText: AppCompatEditText) {
        this.editText = editText
        this.editText.addTextChangedListener(this)
    }

    override fun afterTextChanged(editable: Editable?) {
        editText.removeTextChangedListener(this)
        val formattedRut = RutFormatter.displayFormat(editable.toString())
        editText.apply {
            text?.clear()
            append(formattedRut)
            setSelection(formattedRut.length)
        }
        editText.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
}
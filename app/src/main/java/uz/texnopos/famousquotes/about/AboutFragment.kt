package uz.texnopos.famousquotes.about

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_about.*
import uz.texnopos.famousquotes.R

class AboutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        about_text.movementMethod = LinkMovementMethod.getInstance()
//        gmail.setOnClickListener {
//            val i = Intent(Intent.ACTION_SEND)
//            i.type = "message/rfc822"
//            i.putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com"))
//            i.putExtra(Intent.EXTRA_SUBJECT, "subject of email")
//            i.putExtra(Intent.EXTRA_TEXT, "body of email")
//            try {
//                startActivity(Intent.createChooser(i, "Send mail..."))
//            } catch (ex: ActivityNotFoundException) {
//                Toast.makeText(requireContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

}
package com.envercelik.trivia

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.envercelik.trivia.databinding.FragmentGameOverBinding
import com.envercelik.trivia.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_game_won,container,false
        )


        binding.nextMatchButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
        }

        val args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }

        if (args != null) {
            Toast.makeText(context,"NumCorrect: ${args.numCorrect},NumQuestions: ${args.numQuestions}",Toast.LENGTH_LONG).show()
        }

        setHasOptionsMenu(true)


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu,menu)
    }

    private fun getShareIntent() : Intent? {
        val args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
        /*
        if (args != null) {
            return activity?.let {
                ShareCompat.IntentBuilder.from(it)
                    .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
                    .setType("text/plain")
                    .intent
            }
        }
        */

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        if (args != null) {
            shareIntent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
        }

        return shareIntent


    }


    private fun shareSuccess() {
        startActivity(getShareIntent())
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }


}
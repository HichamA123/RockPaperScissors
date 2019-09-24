package com.example.rockpaperscissors.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    lateinit var context : Context

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_game, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return games.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }


    inner class ViewHolder (itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(game: Game) {
            itemView.tvDate.text = game.date
            itemView.tvGameResult.text = context.getString(game.result.stringId)
            itemView.tvComputerMove.foreground = context.getResources().getDrawable(game.computerMove.image)
            itemView.tvUserMove.foreground = context.getResources().getDrawable(game.userMove.image)
        }
    }
}

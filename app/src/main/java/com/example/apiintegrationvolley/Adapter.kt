import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiintegrationvolley.MainActivity
import com.example.apiintegrationvolley.R
import com.example.apiintegrationvolley.userInfoItem

class Adapter(
    val context: Context,
    val userInfo: List<userInfoItem>,
    val listener: MainActivity
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // Define an interface for click handling
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Use Glide to load API images
        Glide.with(context).load(userInfo[position].avatar_url).into(holder.image)
        holder.text.text = userInfo[position].login

        // Set click listener for the item
        holder.itemView.setOnClickListener {
            listener.onItemClick(position) // Call the interface method
        }
    }

    override fun getItemCount(): Int {
        return userInfo.size
    }

    // Define your custom ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.img)
        val text: TextView = itemView.findViewById(R.id.txt)
    }
}

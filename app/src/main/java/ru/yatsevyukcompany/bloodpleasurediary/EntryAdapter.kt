package ru.yatsevyukcompany.bloodpleasurediary



import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.yatsevyukcompany.bloodpleasurediary.db.Entry


class EntryAdapter(private val entries: List<Entry>) : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fieldUpTextView: TextView = itemView.findViewById(R.id.textUp)
        val fieldDownTextView: TextView = itemView.findViewById(R.id.textDown)
        val fieldPulseTextView: TextView = itemView.findViewById(R.id.textPulse)
        val fieldStatusTextView: TextView = itemView.findViewById(R.id.textStatus)
        val fieldDateTextView: TextView = itemView.findViewById(R.id.textDate)


    }
    private var currentTheme: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = entries[position]
        holder.fieldUpTextView.text = entry.fieldUp
        holder.fieldDownTextView.text = entry.fieldDown
        holder.fieldPulseTextView.text = entry.fieldPusle
        holder.fieldStatusTextView.text = entry.fieldStatus
        holder.fieldDateTextView.text = entry.timestamp
        applyThemeToItem(holder)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, EditEntryActivity::class.java).apply {
                putExtra("fieldUp", entry.fieldUp)
                putExtra("fieldDown", entry.fieldDown)
                putExtra("fieldPulse", entry.fieldPusle)
                putExtra("fieldStatus", entry.fieldStatus)
                putExtra("fieldDate", entry.timestamp)
                putExtra("entryId", entry.id)
            }
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int = entries.size

    fun applyTheme(theme: String) {
        currentTheme = theme
        notifyDataSetChanged()
    }


    private fun applyThemeToItem(holder: EntryViewHolder) {
        when (currentTheme) {

            ThemeManager.THEME_BLUE -> {
                holder.itemView.setBackgroundResource(R.drawable.banner_blue)
            }
            ThemeManager.THEME_GREEN -> {
                holder.itemView.setBackgroundResource(R.drawable.banner_green)
            }
            ThemeManager.THEME_ORANGE -> {
                holder.itemView.setBackgroundResource(R.drawable.banner_orange)
            }
        }
    }

}

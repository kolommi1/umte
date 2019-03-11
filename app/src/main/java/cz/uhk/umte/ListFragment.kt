package cz.uhk.umte

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cz.uhk.umte.model.User
import cz.uhk.umte.model.UserDB
import cz.uhk.umte.model.UserDao
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    val list = mutableListOf<User>()
    val dbList = mutableListOf<UserDB>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbList.addAll(UserDao().selectAll())

        val adapter = Adapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        editTextButton.setOnClickListener {
            val user = User(editText.text.toString(), "Přijmení", 52, 95.6f)
            list.add(user)

            val userDB = UserDB()
            userDB.name = editText.text.toString()
            userDB.lastName = "Přijmení"
            userDB.age = 52
            userDB.weight = 95.6f
            dbList.add(userDB)
            UserDao().createOrUpdate(userDB)

            editText.setText("")
            adapter.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 111 && resultCode == Activity.RESULT_OK){
            val user = data?.getSerializableExtra("user") as UserDB
            val index = data.getIntExtra("index",0)
           // list[index] = user as User
            dbList[index] = user
            UserDao().createOrUpdate(user)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    inner class Adapter : RecyclerView.Adapter<Adapter.Holder>(){

        override fun onCreateViewHolder(root: ViewGroup, p1: Int): Holder {
            return Holder(LayoutInflater.from(context).inflate(R.layout.row_user, root, false))
        }

        override fun onBindViewHolder(holder: Holder, p0: Int) {
            holder.onBind()
        }

        override fun getItemCount(): Int {
            return dbList.size
        }

        inner class Holder(val item: View) : RecyclerView.ViewHolder(item),
            View.OnClickListener,
        View.OnLongClickListener{

            init {
                item.setOnClickListener(this)
                item.setOnLongClickListener(this)
            }

            override fun onClick(v: View?) {
                val intent = Intent(context, UserDetailActivity::class.java)
                intent.putExtra("user", dbList[layoutPosition] )
                intent.putExtra("index", layoutPosition )
                startActivityForResult(intent,111)
            }

            override fun onLongClick(v: View?): Boolean{
                val user = dbList[layoutPosition]
                UserDao().delete(user)
                dbList.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)
                return true
            }

            fun onBind(){
                val textView = item.findViewById<TextView>(R.id.nameTextView)
                val user = dbList[layoutPosition]

                textView.text = user.name
            }
        }
    }

}
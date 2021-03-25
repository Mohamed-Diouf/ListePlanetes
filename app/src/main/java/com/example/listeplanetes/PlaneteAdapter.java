package com.example.listeplanetes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

 class PlanetAdapter extends BaseAdapter {
    ListView listView;
    static int verif = 0;
    Data data;
    MainActivity activity;
    public PlanetAdapter(MainActivity mainActivity){
        this.activity = mainActivity;
        this.data = new Data();
    }

    @Override
    public int getCount()
    {
        return data.getPlanetes().size();
    }

    @Override
    public Object getItem(int arg0)
    {
        return data.getPlanetes().get(arg0);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View itemView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listitem, null);
        }

        TextView nomPlanete = (TextView) itemView.findViewById(R.id.textView);
        nomPlanete.setText((CharSequence) data.getPlanetes().get(position));

        final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);

        final Spinner spinner = (Spinner) itemView.findViewById(R.id.spinner);
        String[] taillePlanetes = {"4900", "12000", "12800", "6800", "144000", "120000", "52000", "50000", "2300"};
        final ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, taillePlanetes);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox checkBox = (CheckBox) compoundButton.findViewById(R.id.checkbox);
                if (checkBox.isChecked()) {
                    spinner.setEnabled(false);
                    spinadapter.notifyDataSetChanged();
                    verif++;
                } else {
                    spinner.setEnabled(true);
                    spinadapter.notifyDataSetChanged();
                    verif--;
                }

                if(verif == 9){
                    activity.btn.setEnabled(true);
                }
                else{
                    activity.btn.setEnabled(false);
                }
                Toast.makeText(activity, "" +verif ,Toast.LENGTH_SHORT).show();
            }
        });



        return itemView;


    }
}

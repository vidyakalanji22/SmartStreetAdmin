package com.msd.smartstreet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.msd.utils.TreeInfo;

import java.util.List;

import admin.msd.com.smartstreetadmin.R;

public class TreeArrayAdapter extends ArrayAdapter<TreeInfo> {

    List<TreeInfo> treeInfoList;

    /**
     * Array adapter class for tree listing
     *
     * @param context
     * @param treeInfoList
     */
    public TreeArrayAdapter(Context context, List<TreeInfo> treeInfoList) {
        super(context, R.layout.tree_list_item, treeInfoList);
        this.treeInfoList = treeInfoList;
    }

    @Override
    public TreeInfo getItem(int position) {

        return treeInfoList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TreeMaintenance t = new TreeMaintenance();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.tree_list_item, parent, false);
        final TextView treeId = (TextView) customView.findViewById(R.id.treeId);
        final TextView treeName = (TextView) customView.findViewById(R.id.treeName);
        final TextView sensorName = (TextView) customView.findViewById(R.id.sensorName);
        final TextView date = (TextView) customView.findViewById(R.id.date);

        TreeInfo tree = getItem(position);
        treeId.setText(tree.getTreeNumber());
        treeName.setText(tree.getTreeName());
        sensorName.setText(tree.getSensorAttached());

        date.setText(tree.getInstalledDate().toString());

        return customView;
    }
}

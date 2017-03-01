package be.howest.pekka.roeselarevrijwilligt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by peksu on 11.11.2016.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

// Adapter for the expandable list

    private Context context;
    private ArrayList<parent_row> parentRowList;
    private ArrayList<parent_row> originalList;

    public MyExpandableListAdapter(Context context, ArrayList<parent_row> originalList) {

        this.context = context;

        this.parentRowList = new ArrayList<>();
        this.parentRowList.addAll(originalList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(originalList);

    }

    @Override
    public int getGroupCount() {
        return parentRowList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentRowList.get(groupPosition).getChildList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentRowList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentRowList.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        parent_row parentRow = (parent_row) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_row, null);
        }

        TextView heading = (TextView) convertView.findViewById(R.id.parent_text);
        heading.setText(parentRow.getName().trim());


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        child_row childRow = (child_row) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_row, null);
        }


        final TextView childText = (TextView) convertView.findViewById(R.id.header_textview);
        childText.setText(childRow.getText().trim());

        final TextView childText2 = (TextView) convertView.findViewById(R.id.employer_name_textview);
        childText2.setText(childRow.getText2().trim());

        final TextView childText3 = (TextView) convertView.findViewById(R.id.job_description_textview);
        childText3.setText(childRow.getText3().trim());

        final TextView childText4 = (TextView) convertView.findViewById(R.id.employer_amount_textview);
        childText4.setText(childRow.getText4().trim());

        final TextView childText5 = (TextView) convertView.findViewById(R.id.persons_amount_textview);


        final View finalConvertView = convertView;


        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        parentRowList.clear();
        if (query.isEmpty()) {
            parentRowList.addAll(originalList);
        } else {
            for (parent_row parentRow : originalList) {
                ArrayList<child_row> childList = parentRow.getChildList();
                ArrayList<child_row> newList = new ArrayList<child_row>();

                for (child_row childRow : childList) {
                    if (childRow.getText().toLowerCase().contains(query)) {
                        newList.add(childRow);
                    }
                }
                if (newList.size() > 0) {
                    parent_row nParentRow = new parent_row(parentRow.getName(), newList);
                    parentRowList.add(nParentRow);
                }

            }
        }
        notifyDataSetChanged();
    }
}
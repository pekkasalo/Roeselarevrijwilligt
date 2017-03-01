package be.howest.pekka.roeselarevrijwilligt;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {



    // This is the bread and butter of the app. This class controls the whole app and it's movements. The basic idea is that the app is used with the Navigationbar and search.
    // In this class the pathing of the navigation is declared, the search is loaded from the database and everything else.




    boolean doublecheck = false;
    //    private SearchManager searchManager;
    private SearchManager searchManager;
    private SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private MyExpandableListAdapter listAdapterEmpty = null;
    private ExpandableListView myList;
    private ArrayList<parent_row> parentList = new ArrayList<parent_row>();
    private ArrayList<parent_row> showTheseParentList = new ArrayList<parent_row>();
    private ArrayList<parent_row> parentListEmpty = new ArrayList<parent_row>();

    private MenuItem searchItem;
    private String json_string;
    private BackgroundWorker backgroundWorker;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    //    private ArrayList<parent_row> showTheseParentList = new ArrayList<parent_row>();
//    private MenuItem searchItem;
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final DrawerLayout mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        parentList = new ArrayList<parent_row>();
        showTheseParentList = new ArrayList<parent_row>();


        // The app will crash if display list is not called here.
        try {
            displayList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // This expands the list.
        // expandAll();
    }


    @Override
    public void onBackPressed() {


        // Checking that if the drawing is opened, and closing that instead of the app if open

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (doublecheck = true) {
            super.onBackPressed();
            setTitle("Roeselare Vrijwilligt");
            doublecheck = false;
        } else if (doublecheck == false) {
            int count = 0;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show();
            while (count < 2) {
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show();
                count++;
                super.onBackPressed();
            }
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Setting the menu

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_drawer, menu);


        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo
                (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        MenuItemCompat.expandActionView(searchItem);
        //searchView.requestFocus();


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:

                setContentView(R.layout.activity_navigation_drawer);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.setDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
                setTitle("Roeselare Vrijwilligt");


                // searchView.requestFocus();
                //clearList();
                try {
                    int count = listAdapter.getGroupCount();
                    Log.d("PAINAESSA SEARCH", String.valueOf(count));
                    if (count > 0) {
                        expandAll();
                        displayList();
                    } else {
                        displayList();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }





            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        doublecheck = true;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_Matched_Vacancies) {

//            login dialog = new login();
//            dialog.show(getSupportFragmentManager(), "nav_matched_vacancies");

            fragmentManager.beginTransaction().addToBackStack("nav_login").replace(R.id.content_frame, new loginmatched()).commit();



        } else if (id == R.id.nav_info_on_volunteering) {

            fragmentManager.beginTransaction().addToBackStack("nav_info_on_volunteering").replace(R.id.content_frame, new Info_on_volunteering_fragment()).commit();
        } else if (id == R.id.nav_myProfile) {

            dialog_fragment dialog = new dialog_fragment();
            dialog.show(getSupportFragmentManager(), "myprofile");

        } else if (id == R.id.nav_partners) {

            fragmentManager.beginTransaction().addToBackStack("nav_partners").replace(R.id.content_frame, new Partners()).commit();
        } else if (id == R.id.nav_Terms_Of_Engagement) {

            fragmentManager.beginTransaction().addToBackStack("nav_terms_of_engagement").replace(R.id.content_frame, new Terms_of_engagement_fragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void SetTitle(String title) {
        this.setTitle(title);
    }

    @Override
    public boolean onClose() {

        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        listAdapter.filterData(query);
        expandAll();

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        listAdapter.filterData(newText);
        expandAll();
        return false;
    }


    private void loadData() throws ExecutionException, InterruptedException, JSONException {


        ArrayList<child_row> ChildRows = new ArrayList<child_row>();

        parent_row parentRow = null;
        parent_row parentRow1 = null;
        //int luku = listAdapter.getGroupCount();

        //  Log.d("loadata", String.valueOf(listAdapter.getGroupCount()));

        String type = "getusers";
        String id = "2";
        String idjson = "", description = "", title = "", wanted = "", summary = "";
        backgroundWorker = new BackgroundWorker(this);
        String jsonString = backgroundWorker.execute(type, id).get();
        jsonObject = new JSONObject(jsonString);
        Log.d("Navigation LoadData: ", jsonString);
        int count = 0;
        jsonArray = jsonObject.getJSONArray("result");
        // Log.d("NAVI LOAD JSONARRAY", jsonArray.toString());
        parentRow = new parent_row("First group", ChildRows);

        Boolean empty = ChildRows.isEmpty();
        ChildRows.clear();
//        if (listAdapter.getGroupCount() < 0) {

        parentRow.getChildList();
        Log.d("onko tyhjä: ", empty.toString());
        Log.d("getchilglist parentrow: ", String.valueOf(parentRow.getChildList()));
        while (count < jsonArray.length()) {
            JSONObject jo = jsonArray.getJSONObject(count);

            idjson = jo.getString("id");
            description = jo.getString("description");
            title = jo.getString("title");
            wanted = jo.getString("wanted");
            summary = jo.getString("summary");
            String descript = description.substring(0, Math.min(description.length(), 80));


            ChildRows.add(new child_row(title, summary, descript + "...", title, wanted, description));


            //parentList.clear();

            count++;
        }
        parentList.add(parentRow);


    }

    private void expandAll() {

        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    private void displayList2() throws ExecutionException, InterruptedException, JSONException {


        loadData();
    }

    private void displayList() throws ExecutionException, InterruptedException, JSONException {


        loadData();

        myList = (ExpandableListView) findViewById(R.id.expandablesearch);
        listAdapterEmpty = new MyExpandableListAdapter(NavigationDrawerActivity.this, parentListEmpty);
        listAdapter = new MyExpandableListAdapter(NavigationDrawerActivity.this, parentList);
        int count2 = listAdapter.getGroupCount();
        Log.d("Displaylist ", myList.toString());
        Log.d("Displaylist ", listAdapter.toString());


        myList.setAdapter(listAdapterEmpty);

        listAdapter.notifyDataSetChanged();

        myList.setAdapter(listAdapter);


        Log.d("Displaylist", String.valueOf(myList.getCount()));
        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                parent_row headerinfo = parentList.get(groupPosition);
                child_row childrow = headerinfo.getChildList().get(childPosition);
                Intent myIntent = new Intent(NavigationDrawerActivity.this, ChildClicked.class);
                myIntent.putExtra("information", childrow.getText());
                myIntent.putExtra("information2", childrow.getText2());
                myIntent.putExtra("information3", childrow.getText3());
                myIntent.putExtra("information4", childrow.getText4());
                myIntent.putExtra("information6", childrow.getText6());
                myIntent.putExtra("information5", childrow.getText5());
                startActivity(myIntent);

                return true;
            }
        });


    }

    public void saveuser(String username, String password) {
        String MY_PREFS = "my_prefs";
        String USERNAME = "username";

        String user = username;
        String pass = password;

        SharedPreferences sprf = this.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sprf.edit();

        editor.putString(USERNAME, user);
        editor.apply();
    }
}

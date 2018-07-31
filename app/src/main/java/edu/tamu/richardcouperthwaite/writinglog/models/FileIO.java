package edu.tamu.richardcouperthwaite.writinglog.models;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FileIO {
//This class needs to have methods that can handle input and output to three files
//The three files are:
//1) projectlist file (JSON)
//2) stats file (JSON)
//3) alldata file (csv)
//
//The projectlist file will contain a list of all projects that have a structure the same
//and the Project class. The input and ouput for this file needs to send back a list of all the
//projects
//
//the stats file will contain the data for the current and previous weeks and month. Will need to
//be able to input and output data to this file as sessions are completed. only need to get the data
//when the stats tab is opened.
//
//The alldata file needs to be a csv type file (for easy export) that records every session in terms
//of start time, end time, date, project, and final comment. could also consider a comment on how the
//session went. This will just record all data at the end of each session and add a new line to the end
//of the file. No output from this file will be necessary at this time.

    public static ArrayList<Project> getProjectDetails(Context context) {
        ArrayList<Project> Projects = new ArrayList<Project>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, "project_list.json"));
            JSONArray projArray = obj.getJSONArray("projects");
            for (int i = 0; i < projArray.length(); i++) {
                JSONObject projDetail = projArray.getJSONObject(i);
                String id = projDetail.getString("id");
                String title = projDetail.getString("Title");
                String description = projDetail.getString("Description");
                String lastcomment = projDetail.getString("LastComment");
                int time = projDetail.getInt("Time");
                String date = projDetail.getString("DueDate");
                Project newProj = new Project(id, title, description, lastcomment, time, date);
                Projects.add(newProj);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        if (Projects.isEmpty()) {
            Projects.add(new Project("0", "Sample Project", "Sample Project", "Last Comment", 0, "01/01/1901"));
        }
        return Projects;
    }

    public static ArrayList<String> getStatisticDetails(Context context) {
        ArrayList<String> stats = new ArrayList<String>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, "statistics.json"));
            JSONArray projArray = obj.getJSONArray("projects");
            JSONObject projDetail = projArray.getJSONObject(0);
            stats.add(projDetail.getString("currentweektime"));
            stats.add(projDetail.getString("currentweekdays"));
            stats.add(projDetail.getString("prevweektime"));
            stats.add(projDetail.getString("prevweekdays"));
            stats.add(projDetail.getString("currentmonthtime"));
            stats.add(projDetail.getString("currentmonthdays"));
            stats.add(projDetail.getString("prevmonthtime"));
            stats.add(projDetail.getString("prevmonthdays"));
            stats.add(projDetail.getString("weekstartdate"));
            stats.add(projDetail.getString("monthstartdate"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return stats;
    }

    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

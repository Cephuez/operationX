package com.example.operationx;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameInfoFragment extends Fragment {
    private GameOptionsViewModel model;
    private SettingsFragment sf;
    private final String[] pauseOptions = {"Resume", "Settings", "Main Menu"};
    private final String[] settingsOptions = {"Volume", "Game FPS", "Redeem"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GameInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameInfoFragment newInstance(String param1, String param2) {
        GameInfoFragment fragment = new GameInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_game_info, container, false);
        Bundle bundle = this.getArguments();
        int level = 0;
        if (bundle != null) {
            level = bundle.getInt("LEVEL_FRAG_INT");
        }

        int lives = 3;

        model = new ViewModelProvider(requireActivity()).get(GameOptionsViewModel.class);
        ImageView mapView = view.findViewById(R.id.map_button);
        registerForContextMenu(mapView);
        addOnclick(view);
        setObservers(view);
        model.getCurrentLevel().setValue(level);
        model.getCurrentLives().setValue(lives);
        return view;
    }

   /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.pause_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.settings_pause:
                System.out.print("pressed settings");
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }*/


    public void setObservers(View view){
        final TextView livesBox =  view.findViewById(R.id.lives_box);
        final TextView levelBox = view.findViewById(R.id.level_box);
        // Create the observer which updates the UI.
        final Observer<Integer> currLivesObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer lives) {
                // Update the UI, in this case, a TextView.
                System.out.println("lives = " + lives);
                livesBox.setText("Lives\n" + lives);
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getCurrentLives().observe(requireActivity(), currLivesObserver);

        final Observer<Integer> currLevelObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer level) {
                // Update the UI, in this case, a TextView.
                System.out.println("level = " + level);
                //Code to update level here ****
                levelBox.setText("Level\n" + 1);
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getCurrentLevel().observe(requireActivity(), currLevelObserver);

    }

    private boolean pausedGame;
    public boolean isPaused(){
        return pausedGame;
    }

    public void addOnclick(View view) {
        TextView livesBox = view.findViewById(R.id.lives_box);

        livesBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                model.getCurrentLives().setValue(2);
            }
        });


        ImageView pause = view.findViewById(R.id.pause_button);

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausedGame = true;
                //Pause the game animation here... <-
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("PAUSED");
                builder.setItems(pauseOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            pausedGame = false;
                            System.out.println("Resume the game animation");
                        }else if(which == 1){
                            /*System.out.println("Start the settings fragment");
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("SETTINGS");
                            builder.setItems(settingsOptions, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.out.println("Launch settings alert menu");
                                }
                            });
                            builder.show();*/
                            sf = new SettingsFragment();
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            fm.beginTransaction()
                                    .replace(R.id.game_layout, sf)
                                    .addToBackStack(null)
                                    .commit();

                        }else if(which == 2){
                            System.out.println("Go to main menu");
                            MainMenuFragment mmFrag = new MainMenuFragment();
                            FragmentManager fm = getParentFragmentManager();
                            fm.beginTransaction().replace(R.id.game_layout, mmFrag).commit();
                        }
                    }
                });
                builder.show();
            }
        });
    }

}
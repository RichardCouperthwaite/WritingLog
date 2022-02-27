package edu.tamu.richardcouperthwaite.writinglog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tamu.richardcouperthwaite.writinglog.databinding.FragmentNewProjectBinding;

public class NewProjectFragment extends Fragment {
    private FragmentNewProjectBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentNewProjectBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCreateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewProjectFragment.this)
                        .navigate(R.id.action_newProjectFragment_to_writingSessionFragment);
            }
        });

        binding.btnCreateReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewProjectFragment.this)
                        .navigate(R.id.action_newProjectFragment_to_mainMenuFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
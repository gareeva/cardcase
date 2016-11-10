package io.bloco.cardcase.presentation.common;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.multiselector.ModalMultiSelectorCallback;
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SelectableHolder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.bloco.cardcase.AndroidApplication;
import io.bloco.cardcase.R;
import io.bloco.cardcase.data.Database;
import io.bloco.cardcase.data.models.Card;
import io.bloco.cardcase.data.models.Category;
import io.bloco.cardcase.presentation.home.HomeActivity;
import io.bloco.cardcase.presentation.home.HomeContract;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    @Bind(R.id.category_name)
    TextView name;

    private HomeContract.View homeContract;
    private Database database;
    private HomeActivity activity;
    private Category category;

    @Inject
    public CategoryViewHolder(View view, HomeContract.View homeContract, Database database, HomeActivity activity) {
        super(view);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        ((AndroidApplication) view.getContext().getApplicationContext()).getApplicationComponent()
                .inject(this);

        this.homeContract = homeContract;
        this.database = database;
        this.activity = activity;
        ButterKnife.bind(this, view);
    }

    public void bind(Category category) {
        this.category = category;
        name.setText(category.getName());
    }

    @Override
    public void onClick(View view) {
        List<Card> cards = database.getReceivedCards();
        List<Card> cardsByCategory = new ArrayList<>();
        for (Card card : cards) {
            if (card.getCategoryId().equals(category.getId())) {
                cardsByCategory.add(card);
            }
        }
        homeContract.hideCategories();
        homeContract.showCards(cardsByCategory);
    }

    //Multi selection
    public MultiSelector multiSelector = new MultiSelector();
    private LinkedHashMap<Integer, Integer> IDmap = new LinkedHashMap<>();

    // Multi select items in recycler view
    public android.support.v7.view.ActionMode.Callback selectMode = new ModalMultiSelectorCallback(multiSelector) {

        @Override
        public boolean onCreateActionMode(android.support.v7.view.ActionMode actionMode, Menu menu) {
            activity.getMenuInflater().inflate(R.menu.selection_menu, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(android.support.v7.view.ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                // On clicking discard reminders
//                case R.id.discard_reminder:
//                    // Close the context menu
//                    actionMode.finish();
//
//                    // Get the reminder id associated with the recycler view item
//                    for (int i = IDmap.size(); i >= 0; i--) {
//                        if (multiSelector.isSelected(i, 0)) {
//                            int id = IDmap.get(i);
//
//                            // Get reminder from reminder database using id
//                            Category temp = categoryAdapter.getCategories().get(id);
//                            // Delete reminder
//                            rb.deleteReminder(temp);
//                            // Remove reminder from recycler view
//                            mAdapter.removeItemSelected(i);
//                            // Delete reminder alarm
//                            mAlarmReceiver.cancelAlarm(getApplicationContext(), id);
//                        }
//                    }
//
//                    // Clear selected items in recycler view
//                    multiSelector.clearSelections();
//                    // Recreate the recycler items
//                    // This is done to remap the item and reminder ids
//                    mAdapter.onDeleteItem(getDefaultItemCount());
//
//                    // Display toast to confirm delete
//                    Toast.makeText(getApplicationContext(),
//                            "Deleted",
//                            Toast.LENGTH_SHORT).show();
//
//                    // To check is there are saved reminders
//                    // If there are no reminders display a message asking the user to create reminders
//                    List<Reminder> mTest = rb.getAllReminders();
//
//                    if (mTest.isEmpty()) {
//                        mNoReminderView.setVisibility(View.VISIBLE);
//                    } else {
//                        mNoReminderView.setVisibility(View.GONE);
//                    }
//
//                    return true;
//
//                // On clicking save reminders
//                case R.id.save_reminder:
//                    // Close the context menu
//                    actionMode.finish();
//                    // Clear selected items in recycler view
//                    multiSelector.clearSelections();
//                    return true;

                default:
                    break;
            }
            return false;
        }
    };

    @Override
    public boolean onLongClick(View view) {
        Log.d("TEST", "testsets");
        AppCompatActivity activity = this.activity;
        activity.startSupportActionMode(selectMode);
        //multiSelector.setSelected( this, true);
        return false;
    }
}

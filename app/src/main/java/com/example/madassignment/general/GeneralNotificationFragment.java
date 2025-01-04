package com.example.madassignment.general;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.madassignment.R;
import java.util.ArrayList;
import java.util.List;

public class GeneralNotificationFragment extends Fragment {

    private RecyclerView notificationsRecyclerView;
    private List<Notification> notificationsList = new ArrayList<>();

    public GeneralNotificationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.general_activity_notifications, container, false);

        notificationsRecyclerView = rootView.findViewById(R.id.RVNotifications);

        notificationsList.add(new Notification("Promotion!", "Are you ready for a 4x session ...", "2024-12-11 10:00"));
        notificationsList.add(new Notification("Medication Reminder", "Don't forget to take your 125g of P...", "2024-12-11 15:30"));
        notificationsList.add(new Notification("peace.my updates", "Your opinion matters! Please let us know...", "2024-12-11 19:00"));

        setupRecyclerView(rootView);

        return rootView;
    }

    private void setupRecyclerView(View rootView) {

        notificationsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationsRecyclerView.setAdapter(new NotificationsAdapter(notificationsList));
    }

    public static class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

        private List<Notification> notifications;

        public NotificationsAdapter(List<Notification> notifications) {
            this.notifications = notifications;
        }

        @Override
        public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_item_notification, parent, false);
            return new NotificationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NotificationViewHolder holder, int position) {
            Notification notification = notifications.get(position);
            holder.title.setText(notification.getTitle());
            holder.message.setText(notification.getMessage());
            holder.timestamp.setText(notification.getTimestamp());
        }

        @Override
        public int getItemCount() {
            return notifications.size();
        }

        public static class NotificationViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView message;
            TextView timestamp;

            public NotificationViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.notificationTitle);
                message = itemView.findViewById(R.id.notificationMessage);
                timestamp = itemView.findViewById(R.id.notificationTimestamp);
            }
        }
    }

    public static class Notification {
        private String title;
        private String message;
        private String timestamp;

        public Notification(String title, String message, String timestamp) {
            this.title = title;
            this.message = message;
            this.timestamp = timestamp;
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }

        public String getTimestamp() {
            return timestamp;
        }
    }
}

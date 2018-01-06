package com.example.zveri.telemedicine;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zveri.telemedicine.api.entities.DoctorProfile;

class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {

    /* The context we use to utility methods, app resources and layout inflaters */
    private final Context mContext;
    /*
     * Below, we've defined an interface to handle clicks on items within this Adapter. In the
     * constructor of our ForecastAdapter, we receive an instance of a class that has implemented
     * said interface. We store that instance in this variable to call the onClick method whenever
     * an item is clicked in the list.
     */
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    private Cursor mCursor;


    /**
     * Creates a ForecastAdapter.
     *
     * @param context      Used to talk to the UI and app resources
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public DoctorListAdapter(@NonNull Context context, ListItemClickListener clickHandler){
        mContext = context;
        mOnClickListener = clickHandler;

    }

    @Override
    public DoctorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View view = LayoutInflater.from(mContext).inflate(R.layout.doctors_list, viewGroup, false);

        view.setFocusable(true);

        return new DoctorViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param docviewholder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */

    @Override
    public void onBindViewHolder(DoctorViewHolder docviewholder, int position) {
        mCursor.moveToPosition(position);

        DoctorProfile dp = new DoctorProfile(null,null,
                null,null,null,null,null);

        // icon
        String doctorPhoto = dp.getPhoto();

        //docviewholder.iconView.setText(doctorPhoto);

        //forecastAdapterViewHolder.iconView.setImageResource(weatherImageId);

        // first & last name
        String doctorName = dp.getFullName();

        docviewholder.nameView.setText(doctorName);

        // speciality

        String speciality = dp.getSpecs();

        docviewholder.descriptionView.setText(speciality);

        //stars

        Double stars = dp.getStars();
        String st = Double.toString(stars);
        docviewholder.adressView.setText(st);

        //adress
        String adress = dp.getAddress();
        docviewholder.adressView.setText(adress);

    }

    @Override
    public int getItemCount() {
        if (null == mCursor) return 0;
        return mCursor.getCount();
    }




    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    class DoctorViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        final ImageView iconView;

        final TextView nameView;
        final TextView descriptionView;
        final TextView starView;
        final TextView adressView;



        public DoctorViewHolder(View view){
            super(view);

            iconView = (ImageView) view.findViewById(R.id.doctor_icon);
            nameView = (TextView) view.findViewById(R.id.name);
            descriptionView = (TextView) view.findViewById(R.id.doctor_description);
            starView = (TextView) view.findViewById(R.id.stars);
            adressView = (TextView) view.findViewById(R.id.adress);

            itemView.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click. We fetch the date that has been
         * selected, and then call the onClick handler registered with this adapter, passing that
         * date.
         *
         * @param v the View that was clicked
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

    }
}

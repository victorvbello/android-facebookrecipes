package com.example.victorbello.facebookrecipes.recipelist.ui.adapter;

/**
 * Created by victorbello on 24/08/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.SendButton;

import java.util.List;
import android.net.Uri;

import com.example.victorbello.facebookrecipes.entities.Recipe;
import com.example.victorbello.facebookrecipes.libs.base.ImageLoader;
import com.example.victorbello.facebookrecipes.R;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private List<Recipe> recipeList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public RecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.recipeList = recipeList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_stored_recipes,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe=recipeList.get(position);

        imageLoader.load(holder.imgRecipe,currentRecipe.getImageURL());
        holder.txtRecipeName.setText(currentRecipe.getTitle());
        holder.imgFav.setTag(currentRecipe.getFavorite());
        if(currentRecipe.getFavorite()){
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_off);
        }
        holder.setOnItemClickListener(currentRecipe,onItemClickListener);


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setRecipes(List<Recipe> recipeList){
        this.recipeList=recipeList;
        notifyDataSetChanged();
    }

    public void removeRecipe(Recipe recipe){
        this.recipeList.remove(recipe);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgRecipe;
        private TextView txtRecipeName;
        private ImageButton imgFav;
        private ImageButton imgDelete;
        private ShareButton fbShare;
        private SendButton fbSend;
        private View view;
        public ViewHolder(View itemView) {

            super(itemView);
            view=itemView;

            imgRecipe=(ImageView) view.findViewById(R.id.imgRecipe);
            txtRecipeName=(TextView) view.findViewById(R.id.txtRecipeName);
            imgFav=(ImageButton) view.findViewById(R.id.imgFav);
            imgDelete=(ImageButton) view.findViewById(R.id.imgDelete);
            fbShare=(ShareButton) view.findViewById(R.id.fbShare);
            fbSend=(SendButton) view.findViewById(R.id.fbSend);

        }

        public void setOnItemClickListener(final Recipe currentRecipe, final OnItemClickListener listener){

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    listener.onItemClick(currentRecipe);
                }
            });

            imgFav.setOnClickListener(new View.OnClickListener(){
               @Override
                public void onClick(View view){
                    listener.onFavClick(currentRecipe);
               }
            });

            imgDelete.setOnClickListener(new View.OnClickListener(){
               @Override
                public void onClick(View view){
                   listener.onDeleteClick(currentRecipe);
               }
            });

            ShareLinkContent content=new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(currentRecipe.getSourceURL()))
                    .build();

            fbShare.setShareContent(content);
            fbSend.setShareContent(content);
        }
    }
}

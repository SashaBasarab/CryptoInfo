package com.example.cryptoinfo.data.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoinfo.R;
import com.example.cryptoinfo.feature.detalization.CoinDetailsActivity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {
    private List<Coin> coins;

    public CoinAdapter(List<Coin> coins) {
        this.coins = coins;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(coins!=null&&!coins.isEmpty()) {
            Coin coin = coins.get(position);
            holder.coinName = coin.getName();
            holder.textViewName.setText("Name: " + coin.getName());
            holder.textViewSymbol.setText("Symbol: " + coin.getSymbol());
            holder.textViewPrice.setText("Price: " + coin.getPriceUsd());
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewSymbol;
        public TextView textViewPrice;
        public Button button;
        public String coinName;

        public ViewHolder(View itemView) {

            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewSymbol = itemView.findViewById(R.id.textViewSymbol);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            button = itemView.findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, CoinDetailsActivity.class);
                    intent.putExtra("coin_name", coinName);
                    context.startActivity(intent);
                }
            });
        }
    }
}

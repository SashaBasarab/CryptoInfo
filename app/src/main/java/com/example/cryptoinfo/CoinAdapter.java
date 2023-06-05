package com.example.cryptoinfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

            String content = "";
//            content += "Name: " + coin.getName() + "\n";
//            content += "Symbol: " + coin.getSymbol() + "\n";
//            content += "Price: " + coin.getPriceUsd() + "\n";
            content += "Name: " + coin.getName() + "\n";
            content += "Symbol: " + coin.getSymbol() + "\n";
            content += "Price: " + coin.getPriceUsd() + "\n";

            holder.textView.setText(content);
            holder.button.setText("Details");
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button);
        }
    }
}

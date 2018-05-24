package gatodedany.example.omen.gatodedany;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener{ //Se agrega el Click

    private Button[][] botones = new android.widget.Button[3][3]; //Matriz de los botones
    private boolean turnojug1 = true; //Cambiar la puntuacion del jugador 1
    private int cuenta, jug1puntos, jug2puntos; //contador de puntos y jugadas
    private TextView txtvjug1, txtvjug2; //textview para X y O de los jugadores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtvjug1 = findViewById(R.id.txtv_jug1);
        txtvjug2 = findViewById(R.id.txtv_jug2);

        for (int i = 0; i < 3; i++){ //Matriz para los botones
            for (int j = 0; j < 3; j++){
                    String IDbotones = "boton_" + i + j;
                    int resID = getResources().getIdentifier(IDbotones, "id", getPackageName()); //Se encuentra el boton
                    botones[i][j] = findViewById(resID);
                    botones[i][j].setOnClickListener(this); //Cada que se de un click
                }
            }
            Button btnreinicio = findViewById(R.id.btn_reinicio);
        btnreinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Creacion del metodo para clicks

            }
        });
        }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(turnojug1){
            ((Button) v).setText("X"); //Mostrar X
        }else {
            ((Button) v).setText("O"); //Mostrar O
        }

        cuenta++; //Aumentar la cuenta
    }
}

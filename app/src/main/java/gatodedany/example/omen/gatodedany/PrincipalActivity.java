package gatodedany.example.omen.gatodedany;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                reiniciarElJuego();

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

        if (ganador()){ //Checa la cuenta del jugador 1 y nos dice si gano y aumenta su contador
            if (turnojug1){
                ganojug1();
            }else {
                ganojug2(); //De lo contrario gana el jugador 2
            }
        } else if (cuenta == 9){
            draw();
        }else{
            turnojug1 = !turnojug1; //Si es que no empatan
        }
    }

    private boolean ganador(){ //Metodo para saber el ganador
        String[][] field = new String[3][3]; //Matriz de las jugadas

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = botones[i][j].getText().toString(); //Convierte el texto a string
            }
        }

        for (int i = 0; i < 3; i++){ //Verifica cada uno de los campos de las filas
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }

        for (int i = 0; i < 3; i++){ //Verifica cada uno de los campos de las columnas
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void ganojug1(){
        jug1puntos++;
        Toast.makeText(this, "!Jugador 1 gano!", Toast.LENGTH_SHORT).show();
        actualizarPuntos(); //Se actualizan sus puntos
        reiniciarPuntos(); //Se reinician sus puntos
    } //metodo para saber si gano el jugador 1

    private void ganojug2(){
        jug2puntos++;
        Toast.makeText(this, "!Jugador 2 gano!", Toast.LENGTH_SHORT).show();
        actualizarPuntos(); //Se actualizan sus puntos
        reiniciarPuntos(); //se reinician sus puntos
    } //metodo para saber si gan el jugador 2

    private void draw(){
        Toast.makeText(this, "!Nadie gano!", Toast.LENGTH_SHORT).show();
        reiniciarPuntos(); //Nadie gana
    } //metodo para dibujar las veces ganadas

    private void actualizarPuntos(){
        txtvjug1.setText("Jugador 1: " + jug1puntos); //se suman los puntos al jugador 1
        txtvjug2.setText("Jugador2" + jug2puntos); //se suman los puntos al jugador 2
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("cuenta", cuenta);
        outState.putInt("jug1puntos", jug1puntos);
        outState.putInt("jug2puntos", jug2puntos);
        outState.putBoolean("turnojug1", turnojug1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        cuenta = savedInstanceState.getInt("cuenta");
        jug1puntos = savedInstanceState.getInt("jug1puntos");
        jug2puntos = savedInstanceState.getInt("jug2puntos");
        turnojug1 = savedInstanceState.getBoolean("turnojug1");

    }

    private void reiniciarPuntos(){ //Se reinician los puntos
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                botones[i][j].setText("");
            }
        }
        cuenta = 0;
        turnojug1 = true;
    }

    private void reiniciarElJuego(){ //Metodo para el reinicio del juego
        jug1puntos = 0;
        jug2puntos = 0;
        actualizarPuntos();
        reiniciarPuntos();
    }
}

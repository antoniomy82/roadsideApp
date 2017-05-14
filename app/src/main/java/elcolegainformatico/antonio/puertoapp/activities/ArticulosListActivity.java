package elcolegainformatico.antonio.puertoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.model.Articulo;
import elcolegainformatico.antonio.puertoapp.model.Infraccion;
import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 7/4/17.
 */

public class ArticulosListActivity extends AppCompatActivity {

    private ListView listArticulos;
    private ArrayList<Articulo> articuloArrayList;
    private ArrayList<Infraccion> sancionesSaved = new ArrayList<>(); //Store sanciones go from SancionesList
    private boolean isReglamento;

    private ImageButton home_custom_bar;
    private TextView text_custom_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Custom title bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_title_bar);


        text_custom_title=(TextView)findViewById(R.id.text_custom_title);
        text_custom_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        text_custom_title.setText(getResources().getString(R.string.ListaArticulos));

        home_custom_bar=(ImageButton) findViewById(R.id.home_custom_bar);

        home_custom_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticulosListActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });



        //Esto lo usamos en la versión sin FireBase, tenemos que pasarla a la siguiente activity
        sancionesSaved = (ArrayList<Infraccion>) getIntent().getSerializableExtra("sancionesSaved");

        isReglamento=getIntent().getBooleanExtra("isReglamento",true);

        setContentView(R.layout.activity_articulos_list); //le asigno su layout
        listArticulos=(ListView) findViewById(R.id.articulos_list); //Busco mi listView activity_articulos_list

        //Meto a manubrio los articulos


        articuloArrayList = new ArrayList<>();

        if(isReglamento){
            loadReglamentos();
        }
        else{
            loadLeyes();
        }

        ArticulosAdapter myAdaptater = new ArticulosAdapter(articuloArrayList,ArticulosListActivity.this.getApplicationContext(),isReglamento);
        listArticulos.setAdapter(myAdaptater);

        //Cuando seleccione algún item de la lista
        listArticulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Articulo mArticulo = articuloArrayList.get(position);

                Intent intent = new Intent(ArticulosListActivity.this, EntidadVehiculoActivity.class);

                intent.putExtra("myArticulo", mArticulo); //Pasamos un objeto articulo Parcelable

                //Solo para versión sin Firebase.
                intent.putExtra("sancionesSaved",sancionesSaved);

                startActivity(intent);
            }

        });
    }

    private void loadReglamentos(){
        articuloArrayList.add(new Articulo("77.1","FALTAS LEVES","Desobediencia a las órdenes de los  Celadores-Guarda muelles o  de sus superiores o la interferencia en sus actuaciones o la falta al respeto a los mismos"));
        articuloArrayList.add(new Articulo("77.2","FALTAS LEVES","Uso de las obras o instalaciones portuarias sin autorización, en  materias no especificadas en números o artículos posteriores"));
        articuloArrayList.add(new Articulo("77.3","FALTAS LEVES","Incumplimiento de los usuarios de las reglas de aplicación de las tarifas por servicios en materia no especificadas,   en  números o artículos posteriores"));
        articuloArrayList.add(new Articulo("77.4","FALTAS LEVES","Retraso en la presentación de datos  a efectos de la liquidación de  servicios prestados  por la Junta, cuya cuantía sea inferior a 300 EUROS"));
        articuloArrayList.add(new Articulo("77.5","FALTAS LEVES","Defectuosa o inadecuada utilización del equipo o instalaciones portuarias en función de sus características y potencias"));
        articuloArrayList.add(new Articulo("77.6","FALTAS LEVES","Traslado  sin  autorización  fuera  del  recinto  portuario  del utillaje de la Junta con o sin realización de trabajos"));
        articuloArrayList.add(new Articulo("77.7","FALTAS LEVES","La ocupación de superficies, almacenes , departamentos o  locales  de  la Junta, sujetos a tarifas por Servicios Específicos, sin autorización   o  por Persona diferente a la autorizada , o su dedicación a usos diferentes a los Autorizados"));
        articuloArrayList.add(new Articulo("77.8","FALTAS LEVES","Incumplimiento de la órdenes de desalojar superficies, almacenes, departamentos o locales de la Junta , sujetos a tarifas por Servicios Específicos por terminación del plazo, o en el fijado  de acuerdo con  las  condiciones de la tarifa "));
        articuloArrayList.add(new Articulo("77.9","FALTAS LEVES","Abusos de  la  utilización  de  los suministros de agua dulce o salada , así como falta de conservación de las instalaciones, grifos abiertos, rotura de mangueras o similares"));
        articuloArrayList.add(new Articulo("77.10","FALTAS LEVES","Incumplimiento de las condiciones establecidas en las autorizaciones para la prestación de servicios por personas o  entidades  diferentes  de la Junta del Puerto y que no estuviese comprendido o especificado en otro  articulo"));
        articuloArrayList.add(new Articulo("77.11","FALTAS LEVES","Acceso de personas a las zonas acotadas o cercadas sin autorización"));
        articuloArrayList.add(new Articulo("77.12","FALTAS LEVES","Acceso de vehículos a las zonas acotadas  o cercadas sin autorización"));
        articuloArrayList.add(new Articulo("77.13","FALTAS LEVES","Acceso de maquinaria y vehículos industriales con medios   propios  de manipulación, a las zonas acotadas o cercadas sin autorización"));
        articuloArrayList.add(new Articulo("77.14","FALTAS LEVES","Circulación de vehículos por la zona portuaria sin las debidas precauciones, en forma peligrosa o sin respetar las señales de tráfico o normas generales establecidas"));
        articuloArrayList.add(new Articulo("77.15","FALTAS LEVES","Circulación de vehículos y maquinaria con carga superiores a las autorizadas tanto totales, como por eje, con ruedas,  cadenas  o  llantas metálicas, o en condiciones que supongan peligrosidad"));
        articuloArrayList.add(new Articulo("77.16","FALTAS LEVES","Circulación de trenes  por  las  vías  existentes  en la zona portuaria sin ajustarse a la programación  acordada  o  sin autorización especial, o incumplimiento  de  los  limites de velocidad o normas de seguridad establecidos"));
        articuloArrayList.add(new Articulo("77.17","FALTAS LEVES","Entorpecimiento del uso de escaleras y rampas o su utilización indebida"));
        articuloArrayList.add(new Articulo("77.18","FALTAS LEVES","Aparcamiento de cualquier clase de vehículos,  sobre  las vías férreas  o de grúas a menos de dos metros del carril más próximo"));
        articuloArrayList.add(new Articulo("77.19","FALTAS LEVES","Depósito de mercancías o cualquier clase de objetos sobre las carreteras y vías de circulación o de grúas, en zonas que impidan su normal utilización"));
        articuloArrayList.add(new Articulo("77.20","FALTAS LEVES","Aparcamiento de vehículos o vagones en lugares diferentes de los expresamente señalizados o autorizados para ello"));
        articuloArrayList.add(new Articulo("77.21","FALTAS LEVES","Aportación de información o datos inexactos al  solicitar la designación de atraque para un buque, que puedan inducir a resoluciones  inapropiadas"));
        articuloArrayList.add(new Articulo("77.22","FALTAS LEVES","Falta de comunicación y de confirmación por escrito de la entrada de un buque dentro de los plazos y en la forma señalada"));
        articuloArrayList.add(new Articulo("77.23","FALTAS LEVES","No  avisar  a  los Servicios  de Explotación de la demora en la llegada y atraque de un buque, o del  plazo  en  que  deben  quedar finalizadas las operaciones"));
        articuloArrayList.add(new Articulo("77.24","FALTAS LEVES","Incumplimiento del ritmo fijado para la descarga o carga de un buque"));
        articuloArrayList.add(new Articulo("77.25","FALTAS LEVES","Aportación de informaciones inexactas que puedan inducir a resoluciones inapropiadas sobre la solicitud de trabajos en horas extraordinarias."));
        articuloArrayList.add(new Articulo("77.26","FALTAS LEVES","Negativa a realizar trabajos en horas extraordinarias, festivos o en turnos no habituales, cuando sean declarados de urgencia, por la Dirección del Puerto"));
        articuloArrayList.add(new Articulo("77.27","FALTAS LEVES","Realizar operaciones no autorizadas de reparaciones de buques, aprovisionamientos y similares"));
        articuloArrayList.add(new Articulo("77.28","FALTAS LEVES","No adoptar los buques las medidas necesarias para evitar los riesgos de causar daños o averías a las obras, instalaciones o utillaje portuario"));
        articuloArrayList.add(new Articulo("77.29","FALTAS LEVES","Entorpecimiento del uso  de  las vías férreas o de grúas u otras instalaciones portuarias con escalas, puntales o elementos similares"));
        articuloArrayList.add(new Articulo("77.30","FALTAS LEVES","Aportación de información o datos inexactos al solicitar  servicios de la Junta para la manipulación de mercancías que puedan inducir a resoluciones inapropiadas"));
        articuloArrayList.add(new Articulo("77.31","FALTAS LEVES","Demora en la iniciación de las operaciones autorizadas, no  avisadas a los Servicios de Explotación con antelación suficiente"));
        articuloArrayList.add(new Articulo("77.32","FALTAS LEVES","Incumplimiento en el ritmo fijado para la realización de operaciones de manipulación de mercancías"));
        articuloArrayList.add(new Articulo("77.33","FALTAS LEVES","Manipulación de mercancías sin adoptar las precauciones necesarias para evitar averías, pérdidas o deterioros  en  las mismas, o riesgo de daños"));
        articuloArrayList.add(new Articulo("77.34","FALTAS LEVES","Causar averías, pérdidas o deterioros a mercancías manipuladas valoradas en cuantía inferior a 300 EUROS"));
        articuloArrayList.add(new Articulo("77.35","FALTAS LEVES","Depósito de mercancías en forma  inadecuadas  de aprovechamiento de espacio"));
        articuloArrayList.add(new Articulo("77.36","FALTAS LEVES","Producir sobre los muelles o pavimentos cargas superiores a los límites fijados"));
        articuloArrayList.add(new Articulo("77.37","FALTAS LEVES","No disponer las protecciones adecuadas para evitar deterioros de mercancías, pavimentos, obras o instalaciones"));
        articuloArrayList.add(new Articulo("77.38","FALTAS LEVES","Depósito de mercancías sin autorización o fuera de las zonas designadas, o demora en el plazo fijado para la retirada o traslado de mercancías depositadas"));
        articuloArrayList.add(new Articulo("77.39","FALTAS LEVES","Abandono de basuras , escombros  o  residuos  de cualquier clase en terrenos, instalaciones, obras o equipos portuarios, falta de  limpieza de las zonas de depósito al levantar las mercancías o cualquier hecho que afecte a la limpieza de los citados bienes"));
        articuloArrayList.add(new Articulo("77.40","FALTAS LEVES","No tomar las precauciones necesarias para que las mercancías  depositadas no puedan producir daños a otras contiguas o situadas en  su zona de influencia"));
        articuloArrayList.add(new Articulo("77.41","FALTAS LEVES","Retirada o intento de  retirada  de  mercancías depositadas sin haber abonado o garantizado el pago de las tarifas , gastos o sanciones que les afecten, o sin haber sido autorizado su levante"));
        articuloArrayList.add(new Articulo("77.42","FALTAS LEVES","No cumplir las precauciones establecidas para el embarque,  tránsito o desembarque de ganado"));
        articuloArrayList.add(new Articulo("77.43","FALTAS LEVES","Descarga de productos de pesca en muelles o  rampas  diferentes  de los habilitados, efectuar su venta fuera de  la  lonja o sitios autorizados a este fin o  contravenir  las  normas  existentes  sobre el uso  de instalaciones pesqueras  siempre  que  no estén específicamente sancionadas en reglamentos especiales"));
        articuloArrayList.add(new Articulo("77.44","FALTAS LEVES","Realizar operaciones complementarias de clasificación, remisión formación y descomposición de unidades de carga , flejado complementario  y  otras  similares,  en  lugares  diferentes de los designados en cada caso por el personal de Celadores-Guardamuelles"));
        articuloArrayList.add(new Articulo("77.45","FALTAS LEVES","Depósito o abandono en lugares no autorizados de utillaje y elementos auxiliares de las operaciones de manipulación de mercancías"));
        articuloArrayList.add(new Articulo("77.46","FALTAS LEVES","Utilización de utillaje o elementos auxiliares  en  las  operaciones de manipulación de mercancías en condiciones deficientes de conservación, o en operaciones para las que no sean idóneos"));
        articuloArrayList.add(new Articulo("77.47","FALTAS LEVES","No adopción de cualquiera de las precauciones generales citadas  en artículo 55, cuando no estén penalizadas según otros números o artículos"));
        articuloArrayList.add(new Articulo("77.48","FALTAS LEVES","Fumar durante la manipulación de  mercancías  combustibles  o  no adoptar las precauciones ordenadas en la prevención de incendios"));
        articuloArrayList.add(new Articulo("77.49","FALTAS LEVES","Incumplimiento de condiciones o prescripciones establecidas en las concesiones o autorizaciones administrativas cuando suponga inadecuada  utilización  del  dominio  público  o  de las correspondientes obras o instalaciones"));
        articuloArrayList.add(new Articulo("77.50","FALTAS LEVES","Incumplimiento de las condiciones o prescripciones establecidas en concesiones o autorizaciones administrativas cuando suponga defectuosa prestación de los servicios"));
        articuloArrayList.add(new Articulo("77.51","FALTAS LEVES","Efectuar actividades indicadas en el artículo 64 sin la previa autorización"));
        articuloArrayList.add(new Articulo("77.52","FALTAS LEVES","Ejecución de cualquiera de las acciones prohibidas con carácter general en artículo 65 cuando no estén penalizadas según otro número o artículo"));
        articuloArrayList.add(new Articulo("77.53","FALTAS LEVES","Causar daños a obras o instalaciones en los puertos o en  el equipo, o en los útiles o efectos, o en cualquier clase de mercancías de  los Organismo Portuario  o  de  terceros, o   sustración o hurtos de los Mismos, valoradas en cuantía inferior a 300 EUROS"));
        articuloArrayList.add(new Articulo("77.54","FALTAS LEVES","Incumplimiento   de  órdenes  o  instrucciones de la Dirección  del Puerto, y personal en  quien  delegue  para  el   cumplimiento o interpretación de lo dispuesto en este Reglamento, y que no estuviese comprendido o especificado en otros artículos "));
        articuloArrayList.add(new Articulo("77.55","FALTAS LEVES","Negativa o entorpecimiento a permitir la inspección por el  personal del organismo portuario de las instalaciones establecidas en la zona portuaria, o  no  aportar los  documentos  comerciales  necesarios para la instrucción de los expedientes de sanciones y realización de pruebas"));

        articuloArrayList.add(new Articulo("78.1","FALTAS GRAVES","Utilización indebida o no autorizada de los Servicios Portuarios"));
        articuloArrayList.add(new Articulo("78.2","FALTAS GRAVES","Presentación de informaciones o datos deformados  o  inexactos  y cualquier acción u omisión que supongan actuaciones encaminadas a defraudar en materia de tarifas o cánones"));
        articuloArrayList.add(new Articulo("78.3","FALTAS GRAVES","Retraso en la presentación de datos  a efectos de la liquidación de los servicios prestados por la Junta, en cuantía superior a 300 EUROS."));
        articuloArrayList.add(new Articulo("78.4","FALTAS GRAVES","No presentación de la información necesaria para la liquidación de la Tarifa por Servicios Generales, por las operaciones realizadas en muelles o instalaciones particulares o en las playas"));
        articuloArrayList.add(new Articulo("78.5","FALTAS GRAVES","Alquiler o cesión no autorizada, directa o encubierta, del uso de almacenes, departamentos o locales de la Junta, sujetos a tarifas por Servicios específicos "));
        articuloArrayList.add(new Articulo("78.6","FALTAS GRAVES","Realización de obras no autorizadas en almacenes,  departamentos o locales de la Junta"));
        articuloArrayList.add(new Articulo("78.7","FALTAS GRAVES","Atraque de buques sin autorización, o en muelle distinto al designado"));
        articuloArrayList.add(new Articulo("78.8","FALTAS GRAVES","No informar expresamente a los Servicios de la Junta de la naturaleza explosiva o peligrosa de la mercancías que transporte o vaya a descargar un buque o no cumplir las normas fijadas para su manipulación "));
        articuloArrayList.add(new Articulo("78.9","FALTAS GRAVES","Incumplimiento  de  las órdenes cursadas  de  desatraque,  cambio de muelle o fondeo de un buque"));
        articuloArrayList.add(new Articulo("78.10","FALTAS GRAVES","Aportación de informes inexactos en la solicitud de trabajos en horas extraordinarias que afecten al atraque de otro buque"));
        articuloArrayList.add(new Articulo("78.11","FALTAS GRAVES","No dejar libre el atraque en el plazo fijado al finalizar las operaciones de carga o descarga de mercancías"));
        articuloArrayList.add(new Articulo("78.12","FALTAS GRAVES","No  permanecer  en  condiciones  de  poder  desatracar, cuando así se ordene, los buque cuyo atraque se haya otorgado con  esa  condición, y todos los buques que transportan mercancías  inflamables,  explosivas o peligrosas"));
        articuloArrayList.add(new Articulo("78.13","FALTAS GRAVES","Mantener atracado a un muelle un buque con peligro de hundimiento"));
        articuloArrayList.add(new Articulo("78.14","FALTAS GRAVES","Efectuar la maniobra de atraque o desatraque en condiciones  que supongan peligro para las obras , instalaciones o equipo  portuario  sin tomar las precauciones necesarias"));
        articuloArrayList.add(new Articulo("78.15","FALTAS GRAVES","Vertido por un buque de residuos o contaminación por el mismo  del espacio portuario"));
        articuloArrayList.add(new Articulo("78.16","FALTAS GRAVES","Salida o intento de salida de un buque, sin haber abonado o garantizado ante la Junta del Puerto las cantidades adeudadas por aplicación de tarifas o valoración de las averías causadas"));
        articuloArrayList.add(new Articulo("78.17","FALTAS GRAVES","Causar averías, pérdidas  o  deterioros a mercancías manipuladas valoradas en cuantía superior a 300 EUROS"));
        articuloArrayList.add(new Articulo("78.18","FALTAS GRAVES","Depositar mercancías explosivas, inflamables  o  peligrosas en espacios diferentes de los designados"));
        articuloArrayList.add(new Articulo("78.19","FALTAS GRAVES","Producir sobre los muelles o pavimentos cargas superiores a  los  límites fijados, con peligro para la seguridad de las obras o instalaciones portuarias"));
        articuloArrayList.add(new Articulo("78.20","FALTAS GRAVES","No informar expresamente a los servicios de la Junta de la naturaleza explosiva, inflamable o peligrosa de las mercancías que se vayan a manejar o no cumplir  las  normas  fijadas para su manipulación o depósito"));
        articuloArrayList.add(new Articulo("78.21","FALTAS GRAVES","Utilización de utillaje o elementos auxiliares en  las  operaciones de mercancías en condiciones deficientes de seguridad "));
        articuloArrayList.add(new Articulo("78.22","FALTAS GRAVES","Fumar, encender  fuegos o  situar  luces  sin protección en zonas en que se manipulen o estén depositadas mercancías  inflamables o explosivas"));
        articuloArrayList.add(new Articulo("78.23","FALTAS GRAVES","Realización de obras o instalaciones en la zona portuaria sin la debida concesión o autorización"));
        articuloArrayList.add(new Articulo("78.24","FALTAS GRAVES","Ocupación de terrenos o ejecución de obras o instalaciones provisionales sin autorización"));
        articuloArrayList.add(new Articulo("78.25","FALTAS GRAVES","Incumplimiento de condiciones o prescripciones establecidas en las concesiones o autorizaciones administrativas cuando suponga deterioro del dominio público, modificación esencial  de  la  utilización permitida del mismo o de las correspondientes obras o instalaciones "));
        articuloArrayList.add(new Articulo("78.26","FALTAS GRAVES","Incumplimiento de las condiciones o prescripciones establecidas en concesiones o autorizaciones administrativas  cuando suponga perjuicios a terceros como consecuencia de la defectuosa prestación de los servicios, si no figura sanción expresa en las citadas condiciones o prescripciones "));
        articuloArrayList.add(new Articulo("78.27","FALTAS GRAVES","Contaminación del espacio terrestre o marítimo de la zona portuaria o, en general, del medio ambiente, del puerto con cualquier clase de depósitos, vertido, emanaciones  o  ruidos  sin los  tratamientos  adecuados debidamente autorizados"));
        articuloArrayList.add(new Articulo("78.28","FALTAS GRAVES","Realización de trabajos, prestación de servicios, ejercicio de cualquier clase de actividades industriales o comerciales  en  la  zona portuaria sin la debida autorización"));
        articuloArrayList.add(new Articulo("78.29","FALTAS GRAVES","Ejercicio en el Puerto de actividades de Consignatario o naviero, Empresas estibadoras  de carga y descarga, vendedores  o  exportadores de pescado, y otras que supongan funciones análogas sin estar inscritos en el censo correspondiente, así  como  el  incumplimiento de las normas dictadas para su actuación en la zona portuaria"));
        articuloArrayList.add(new Articulo("78.30","FALTAS GRAVES","Facturación indebida o incorrecta por servicios prestados o repercusión de servicios formulados por la Junta o por otros Organismos de la Administración"));
        articuloArrayList.add(new Articulo("78.31","FALTAS GRAVES","Causar directamente daños en los terrenos, obras o instalaciones del Puerto, o en el equipo, o en los útiles o efectos, o  en  cualquier tipo De mercancías del Organismo Portuario o de terceros, o hurto o robo De las mismas, valoradas en cuantía superior a 300 EUROS"));
        articuloArrayList.add(new Articulo("78.32","FALTAS GRAVES","Ofensas o injurias de palabras u obra a los Celadores-Guardamuelles o a sus superiores en acto de servicio"));
        articuloArrayList.add(new Articulo("78.33","FALTAS GRAVES","Cualquier infracción leve que constituya peligro  para  las  personas o de las  que  se  deriven daños o perjuicios no superiores a 500.000 Pesetas"));
        articuloArrayList.add(new Articulo("78.34","FALTAS GRAVES","Comisión de infracción leve de la que derive una lesión menos grave para alguna persona"));
        articuloArrayList.add(new Articulo("78.35","FALTAS GRAVES","Reincidencia en las faltas leves señaladas en los números 8,21,28,46,49 y 50 del artículo anterior, o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción"));
        articuloArrayList.add(new Articulo("78.36","FALTAS GRAVES","Reincidencia de las faltas leves señaladas en los números 1,3,5,7,15,19,24,25,27,30,32,34,35,38,41,53,54 y 55 del artículo anterior, o  incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción  "));
        articuloArrayList.add(new Articulo("78.37","FALTAS GRAVES","Reincidencia de las faltas leves señaladas en los  restantes  números del artículo anterior, o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción"));

        articuloArrayList.add(new Articulo("79.1","FALTAS MUY GRAVES","Cualquiera de las infracciones leves o graves, definidas en los artículos anteriores, que ocasionaran lesión grave a alguna persona o daños o perjuicios superiores a 500.000 Pesetas"));
        articuloArrayList.add(new Articulo("79.2","FALTAS MUY GRAVES","Reincidencia de las faltas graves señaladas  en los   números 6, 23 y 25 del artículo anterior , o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción "));
        articuloArrayList.add(new Articulo("79.3","FALTAS MUY GRAVES","Reincidencia de las faltas graves señaladas en los números 2,5,7,8,9,1316,18,20,24,26,27,30 y 32 del artículo anterior o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción"));
        articuloArrayList.add(new Articulo("79.4","FALTAS MUY GRAVES","Reincidencia de las faltas graves señaladas en los restantes números del artículo anterior, o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción"));

    }

    private void loadLeyes(){

        articuloArrayList.add(new Articulo("306","Infracciones leves","Son infracciones leves las acciones u omisiones que, no teniendo la consideración de infracción grave o muy grave, por su trascendencia o por la importancia de los daños ocasionados, estén tipificadas en alguno de los siguientes supuestos"));
        articuloArrayList.add(new Articulo("306.1.a","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","El incumplimiento de las disposiciones establecidas en el Reglamento de Explotación y Policía del puerto."));
        articuloArrayList.add(new Articulo("306.1.b","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","El incumplimiento de las ordenanzas establecidas o instrucciones dadas por la Autoridad Portuaria en relación con las operaciones marítimas en el ámbito del puerto."));
        articuloArrayList.add(new Articulo("306.1.c","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","La realización de estas operaciones marítimas en el ámbito portuario con peligro para las obras, instalaciones, equipo portuario u otros buques, o sin tomar las precauciones necesarias."));
        articuloArrayList.add(new Articulo("306.1.d","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","El incumplimiento de las ordenanzas establecidas o instrucciones dadas por la Autoridad Portuaria en lo que se refiere a operaciones de estiba y desestiba, carga y descarga, almacenamiento, entrega y recepción y cualesquiera otras relacionadas con la mercancía."));
        articuloArrayList.add(new Articulo("306.1.e","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","La utilización no autorizada, inadecuada o sin las condiciones de seguridad suficientes, de los equipos portuarios, ya sean de la Autoridad Portuaria o de particulares."));
        articuloArrayList.add(new Articulo("306.1.f","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","El incumplimiento de las ordenanzas o instrucciones dadas por la Autoridad Portuaria en el ámbito de sus competencias sobre la ordenación de los tráficos y modos de transporte terrestre o marítimo."));
        articuloArrayList.add(new Articulo("306.1.g","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","La información incorrecta facilitada a la Autoridad Portuaria sobre los tráficos de buques, mercancías, pasajeros y vehículos de transporte terrestre, especialmente sobre los datos que sirvan de base para la aplicación de las tarifas portuarias."));
        articuloArrayList.add(new Articulo("306.1.h","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","Causar por negligencia o dolo directamente daños a las obras, instalaciones, equipos, mercancías, contenedores y medios de transporte marítimos o terrestres, situados en la zona portuaria."));
        articuloArrayList.add(new Articulo("306.1.i","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","El incumplimiento de la normativa o de las instrucciones que en materia de seguridad marítima o de contaminación se dicten por los órganos competentes."));
        articuloArrayList.add(new Articulo("306.1.j","Infracciones leves:\nEn lo que se refiere al uso del puerto y sus instalaciones","Cualquier otra actuación u omisión que cause daños o menoscabo a los bienes del dominio público portuario, o a su uso o explotación."));

        articuloArrayList.add(new Articulo("306.2.a","Infracciones leves:\nEn lo que se refiere a las actividades sujetas a previa autorización, concesión o prestadas mediante licencia","El incumplimiento de las condiciones de los correspondientes títulos administrativos, de las licencias que habiliten para la prestación de servicios portuarios o de los Pliegos de Prescripciones Particulares que los regulen, sin perjuicio de su caducidad o rescisión."));
        articuloArrayList.add(new Articulo("306.2.b","Infracciones leves:\nEn lo que se refiere a las actividades sujetas a previa autorización, concesión o prestadas mediante licencia","La publicidad exterior no autorizada en el espacio portuario."));
        articuloArrayList.add(new Articulo("306.2.c","Infracciones leves:\nEn lo que se refiere a las actividades sujetas a previa autorización, concesión o prestadas mediante licencia","El suministro incorrecto o deficiente de información a la Autoridad Portuaria, por propia iniciativa o a requerimiento de ésta."));
        articuloArrayList.add(new Articulo("306.2.d","Infracciones leves:\nEn lo que se refiere a las actividades sujetas a previa autorización, concesión o prestadas mediante licencia","El incumplimiento parcial o total de otras obligaciones establecidas en la presente ley y en las disposiciones que la desarrollen y apliquen, y la omisión de actos que fueren obligatorios conforme a ellas."));
        articuloArrayList.add(new Articulo("306.2.e","Infracciones leves:\nEn lo que se refiere a las actividades sujetas a previa autorización, concesión o prestadas mediante licencia","El incumplimiento de los Reglamentos de Explotación y Policía del puerto, del Reglamento General de Practicaje Portuario y demás normas reglamentarias que regulen actividades portuarias."));

        articuloArrayList.add(new Articulo("306.3.a","Infracciones leves:\nInfracciones contra la seguridad marítima","Las acciones de las personas embarcadas que, en estado de ebriedad o bajo la influencia de sustancias psicotrópicas, drogas tóxicas o estupefacientes, pongan en peligro la seguridad del buque."));
        articuloArrayList.add(new Articulo("306.3.b","Infracciones leves:\nInfracciones contra la seguridad marítima","Los actos contrarios a las normas reglamentarias u órdenes dictadas por el capitán u oficialidad del buque que puedan perturbar la seguridad de la navegación."));

        articuloArrayList.add(new Articulo("306.4.a","Infracciones leves:\nInfracciones contra la ordenación del tráfico marítimo","La falta de presentación por parte del capitán, o de la persona que deba hacerlo, de la documentación exigida"));
        articuloArrayList.add(new Articulo("306.4.b","Infracciones leves:\nInfracciones contra la ordenación del tráfico marítimo","El incumplimiento de las normas reglamentarias en materia de Marina Mercante sobre carga o descarga de mercancía a bordo o embarque o desembarque de pasajeros."));
        articuloArrayList.add(new Articulo("306.4.c","Infracciones leves:\nInfracciones contra la ordenación del tráfico marítimo","La utilización, dentro del puerto, de señales acústicas no autorizadas por el correspondiente reglamento."));
        articuloArrayList.add(new Articulo("306.4.d","Infracciones leves:\nInfracciones contra la ordenación del tráfico marítimo","El incumplimiento del deber de facilitar la información que deba ser suministrada a la Autoridad Marítima, por propia iniciativa o a requerimiento de ésta, o el hacerlo de manera incorrecta o deficiente."));

        articuloArrayList.add(new Articulo("306.5.a","Infracciones leves:\nInfracciones relativas a la contaminación del medio marino","El incumplimiento de las normas o la inobservancia de las prohibiciones contenidas en los Reglamentos de Explotación y Policía de los puertos o de otras normas sobre mantenimiento de la limpieza de las aguas o aprovechamientos comunes del medio marítimo."));
        articuloArrayList.add(new Articulo("306.5.b","Infracciones leves:\nInfracciones relativas a la contaminación del medio marino","La realización de reparaciones, carenas y recogidas susceptibles de causar contaminación en contravención de la normativa aplicable."));
        articuloArrayList.add(new Articulo("306.5.c","Infracciones leves:\nInfracciones relativas a la contaminación del medio marino","El incumplimiento de la normativa y de las instrucciones dictadas por la Autoridad competente en relación con las obligaciones de entrega de residuos generados por los buques y residuos de carga."));

        articuloArrayList.add(new Articulo("307","Infracciones graves:","Son infracciones graves las acciones u omisiones tipificadas en el artículo anterior, cuando supongan lesión a alguna persona que motive baja por incapacidad laboral no superior a siete días, o daños o perjuicios superiores a 1.200 euros e inferiores a 6.000 euros, las que pongan en peligro la seguridad del buque o de la navegación, la reincidencia en cualquiera de las faltas tipificadas como leves antes del plazo establecido para su prescripción y, en todo caso, las siguientes:"));
        articuloArrayList.add(new Articulo("307.1.a","Infracciones graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","Las que supongan o impliquen riesgo grave para las personas."));
        articuloArrayList.add(new Articulo("307.1.b","Infracciones graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","El vertido no autorizado desde buques o artefactos flotantes de productos sólidos, líquidos o gaseosos en la Zona II, exterior de las aguas portuarias."));
        articuloArrayList.add(new Articulo("307.1.c","Infracciones graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","El incumplimiento de la normativa establecida para las operaciones de estiba o desestiba, así como el incumplimiento de la obligación legal o en su caso del compromiso, relativo a la contratación de determinado porcentaje de trabajadores en régimen laboral común."));
        articuloArrayList.add(new Articulo("307.1.d","Infracciones graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","El incumplimiento de las normas, ordenanzas e instrucciones sobre la manipulación y almacenamiento en tierra de mercancías peligrosas o la ocultación de éstas o de su condición."));
        articuloArrayList.add(new Articulo("307.1.e","Infracciones graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","El ofrecimiento o entrega de dinero u otro tipo de regalos o dádivas al personal de la Autoridad Portuaria o Marítima, con objeto de captar su voluntad, así como la solicitud, exigencia o aceptación por el personal de estas entidades de dádivas, obsequios, regalos o dinero."));
        articuloArrayList.add(new Articulo("307.1.f","Infracciones graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","La obstrucción al ejercicio de las funciones de policía que correspondan a la Autoridad Portuaria o Marítima."));
        articuloArrayList.add(new Articulo("307.1.g","Infracciones graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","El falseamiento de la información suministrada a la Autoridad Portuaria por propia iniciativa o a requerimiento de ésta."));
        articuloArrayList.add(new Articulo("307.1.h","Infracciones graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","La omisión por el capitán de solicitar los servicios de practicaje o remolcadores que resulten obligatorios según las disposiciones vigentes."));

        articuloArrayList.add(new Articulo("307.2.a","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","Las riñas y pendencias entre las personas embarcadas cuando afecten a la seguridad del buque o de la navegación."));
        articuloArrayList.add(new Articulo("307.2.b","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","Los actos contrarios a las normas reglamentarias u órdenes dictadas por el capitán u oficiales, susceptibles de perjudicar gravemente la seguridad del buque o de la navegación."));
        articuloArrayList.add(new Articulo("307.2.c","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","Portar armas, aparatos o sustancias peligrosas sin la previa autorización del capitán del buque."));
        articuloArrayList.add(new Articulo("307.2.d","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","Las acciones u omisiones de cualquier miembro de la tripulación del buque mientras se halle en estado de ebriedad o bajo la influencia de sustancias psicotrópicas, drogas tóxicas o estupefacientes a consecuencia de los cuales se pueda alterar su capacidad para desempeñar sus funciones."));
        articuloArrayList.add(new Articulo("307.2.e","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","La negativa del capitán a mantener a bordo un polizón hasta su entrega a las autoridades competentes o a las que éstas dispongan."));
        articuloArrayList.add(new Articulo("307.2.f","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","La omisión injustificada por el capitán, o por quien desempeñe el mando en sustitución de aquél, en caso de abordaje, de dar información referente al nombre y puerto de matrícula del buque que se halla bajo su mando, lugar de procedencia y de destino."));
        articuloArrayList.add(new Articulo("307.2.g","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","El embarque clandestino a bordo de un buque español."));
        articuloArrayList.add(new Articulo("307.2.h","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","Traspasar los capitanes, patrones u otro personal marítimo los límites de atribuciones que correspondan a la titulación profesional o de recreo que posean, o contratar o permitir ejercer las funciones de capitán, patrón u oficial encargado de la guardia durante la navegación, a quienes no se encuentren en posesión de titulación suficiente que legalmente les habilite para ello, así como ejercer sin la referida titulación tales funciones."));
        articuloArrayList.add(new Articulo("307.2.i","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","La falta de comunicación por los interesados a la Capitanía Marítima más próxima, salvo causa justificada, del cese de la situación de peligro de un buque o plataforma fija que hubiera ocasionado su petición de socorro."));
        articuloArrayList.add(new Articulo("307.2.j","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","La falta de conocimiento o cumplimiento por parte de los miembros de la dotación de todo buque civil español de sus obligaciones y funciones atribuidas en el correspondiente cuadro orgánico para situaciones de siniestro, aprobado por la Administración de acuerdo con los reglamentos aplicables."));
        articuloArrayList.add(new Articulo("307.2.k","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","El incumplimiento por los navieros, capitanes y patrones de las normas sobre reconocimientos y certificados del buque y de sus elementos, así como la negativa u obstrucción a ser inspeccionados y a colaborar con la inspección cuando sean requeridos."));
        articuloArrayList.add(new Articulo("307.2.l","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","La navegación, salvo causa de fuerza mayor, realizada por cualquier clase de buque, embarcación o artefacto destinado a usos de transporte, pesca o de recreo fuera de los canales balizados de acceso a la costa, en las zonas marcadas como reservadas al baño y debidamente balizadas, así como la navegación en la franja de mar contigua a la costa de una anchura de doscientos metros en las playas y cincuenta metros en el resto de la costa, excediendo el límite de velocidad que marquen las disposiciones vigentes."));
        articuloArrayList.add(new Articulo("307.2.m","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","El incumplimiento de las normas sobre protección marítima por los navieros, capitanes, oficiales o algún otro miembro de la dotación."));
        articuloArrayList.add(new Articulo("307.2.n","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","El incumplimiento del deber de comunicación de los accidentes e incidentes marítimos; la obstaculización de las investigaciones de la Comisión Permanente de Investigación de Accidentes e Incidentes Marítimos; la simulación, ocultación, alteración o destrucción de datos, registros, grabaciones, materiales, informaciones y documentos útiles para las investigaciones de la Comisión Permanente de Investigación de Accidentes e Incidentes Marítimos."));
        articuloArrayList.add(new Articulo("307.2.ñ","Infracciones graves:\nInfracciones contra la seguridad y protección marítimas:","Las acciones u omisiones no comprendidas en los apartados anteriores que pongan en peligro la seguridad del buque o de la navegación."));

        articuloArrayList.add(new Articulo("307.3.a","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","El incumplimiento de las normas vigentes sobre el uso en los buques del pabellón nacional o contraseñas."));
        articuloArrayList.add(new Articulo("307.3.b","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","Navegar los buques sin llevar el nombre, número OMI y folio de inscripción reglamentaria cuando proceda."));
        articuloArrayList.add(new Articulo("307.3.c","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","La carencia, deterioro o inexactitud grave de la documentación reglamentaria del buque."));
        articuloArrayList.add(new Articulo("307.3.d","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","La realización sin la debida autorización de actividades comerciales portuarias, de comercio exterior o interautonómico en puertos, lugares de la costa o situaciones de fondeo en aguas interiores o mar territorial."));
        articuloArrayList.add(new Articulo("307.3.e","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","Incumplir las instrucciones de las Capitanías Marítimas en el ámbito de sus competencias, sobre maniobras y navegación de los buques en los puertos, radas u otras aguas marítimas no portuarias."));
        articuloArrayList.add(new Articulo("307.3.f","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","Incumplir las normas reglamentarias o las instrucciones de las Capitanías Marítimas sobre régimen y tráfico de embarcaciones, incluso de recreo o dedicadas a cualquier uso, y sobre el empleo de todo artefacto cuya utilización pueda significar riesgo para la navegación o para las personas."));
        articuloArrayList.add(new Articulo("307.3.g","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","Incumplir las normas sobre despacho de buques y embarcaciones o sobre enrolamiento de tripulaciones y régimen del rol ante las Capitanías Marítimas y oficinas consulares."));
        articuloArrayList.add(new Articulo("307.3.h","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","El ejercicio de las industrias marítimas a flote incumpliendo las normas sobre inscripción marítima, así como la falta de libreta o de cualquier otro documento o requisito reglamentario exigido para el ejercicio de la profesión."));
        articuloArrayList.add(new Articulo("307.3.i","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","La infracción de las normas sobre inscripción de los buques, embarcaciones o plataformas fijas en las correspondientes listas del Registro de Buques y Empresas Navieras y la utilización de unos u otras en tráficos o actividades no permitidas por las inscripciones."));
        articuloArrayList.add(new Articulo("307.3.j","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","La infracción de las normas sobre utilización de estaciones y servicios radioeléctricos por los buques."));
        articuloArrayList.add(new Articulo("307.3.k","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","El incumplimiento de la obligación de inscripción de las empresas en el Registro de Buques y Empresas Navieras, o de dar cuenta al mismo de los actos, contratos o acuerdos que deban ser inscritos o anotados."));
        articuloArrayList.add(new Articulo("307.3.l","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","La construcción de un buque o la realización de obras de transformación o cambio de motor sin la autorización administrativa estatal que corresponda o con infracción de las normas que la regulan, así como la botadura sin el permiso correspondiente."));
        articuloArrayList.add(new Articulo("307.3.m","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","La infracción de las normas reglamentarias sobre desguace de los buques y sobre destrucción o abandono de las plataformas fijas en aguas situadas en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción."));
        articuloArrayList.add(new Articulo("307.3.n","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","El incumplimiento de las condiciones establecidas en las concesiones o autorizaciones de prestación de servicios marítimos."));
        articuloArrayList.add(new Articulo("307.3.ñ","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","El incumplimiento del deber de facilitar la información que reglamentariamente se deba suministrar a las autoridades marítimas o hacerlo de modo incorrecto."));
        articuloArrayList.add(new Articulo("307.3.o","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","Navegar sin sistemas de señalización reglamentariamente establecidos que permitan la localización y visualización permanente del buque o embarcación o artefacto destinado a usos de transporte, pesca o de recreo."));
        articuloArrayList.add(new Articulo("307.3.p","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","Navegar sin haber obtenido la patente de navegación, pasavante o documento acreditativo de la nacionalidad del buque o embarcación."));
        articuloArrayList.add(new Articulo("307.3.q","Infracciones graves:\nInfracciones contra la ordenación del tráfico marítimo:","Navegar sin que el buque o embarcación o artefacto destinado a usos de transporte, pesca o de recreo se halle debidamente matriculado, o con los certificados reglamentarios caducados."));

        articuloArrayList.add(new Articulo("307.4.a","Infracciones graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","La evacuación negligente en aguas situadas en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción, de desechos u otras sustancias desde buques, plataformas fijas u otras construcciones en la mar cuando se produzca en contravención de la legislación vigente sobre la materia."));
        articuloArrayList.add(new Articulo("307.4.b","Infracciones graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","El incumplimiento de las normas especiales sobre navegación, manipulación de la carga y seguro obligatorio de buques que transporten hidrocarburos u otras sustancias contaminantes."));
        articuloArrayList.add(new Articulo("307.4.c","Infracciones graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","El incumplimiento de las disposiciones vigentes sobre elementos, instalaciones y documentos a bordo para la prevención y el control de las operaciones de evacuación de desechos u otras sustancias."));
        articuloArrayList.add(new Articulo("307.4.d","Infracciones graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","La falta de comunicación inmediata a la Capitanía Marítima más próxima o a la Dirección General de la Marina Mercante, en los casos y en los términos previstos en la legislación aplicable, de los vertidos y evacuaciones contaminantes que se produzcan desde los buques o desde las plataformas fijas u otras instalaciones que se encuentren en aguas situadas en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción."));
        articuloArrayList.add(new Articulo("307.4.e","Infracciones graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","La introducción negligente, de modo directo o indirecto en el medio marino de sustancias, materiales o formas de energía que puedan constituir un peligro para la salud humana, perjudicar los recursos turísticos, paisajísticos o biológicos y la vida marina, reducir las posibilidades de esparcimiento u obstaculizar otros usos legales de los mares, en la medida que dicha introducción fuera contraria a la legislación vigente o no contase con la debida autorización."));
        articuloArrayList.add(new Articulo("307.4.f","Infracciones graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","Las acciones u omisiones no comprendidas en los apartados anteriores que constituyan un riesgo potencial de producir la contaminación del medio marino."));

        articuloArrayList.add(new Articulo("307.5.a","Infracciones graves:\nInfracciones en la prestación de servicios portuarios:","Incumplimiento de las obligaciones de mantener los niveles de rendimiento y de calidad para la prestación de los servicios portuarios."));
        articuloArrayList.add(new Articulo("307.5.b","Infracciones graves:\nInfracciones en la prestación de servicios portuarios:","Utilización de medios distintos de los consignados en la licencia sin autorización, cuando se causen daños a la prestación del servicio."));
        articuloArrayList.add(new Articulo("307.5.c","Infracciones graves:\nInfracciones en la prestación de servicios portuarios:","Negativa u obstrucción a ser inspeccionado y a colaborar con la inspección cuando sea requerida."));
        articuloArrayList.add(new Articulo("307.5.d","Infracciones graves:\nInfracciones en la prestación de servicios portuarios:","Incumplimiento de los requerimientos de información formulados por la Autoridad Portuaria."));
        articuloArrayList.add(new Articulo("307.5.e","Infracciones graves:\nInfracciones en la prestación de servicios portuarios:","Transmisión total o parcial de las licencias sin autorización."));

        articuloArrayList.add(new Articulo("308","Infracciones muy graves:","Son infracciones muy graves las acciones u omisiones tipificadas en los dos artículos anteriores cuando ocasionen lesión a alguna persona que motive baja por incapacidad laboral superior a siete días, o daños o perjuicios superiores a 6.000 euros, las que pongan en grave peligro la seguridad del buque o de la navegación, la reincidencia en cualquiera de las infracciones tipificadas como graves antes del plazo establecido para su prescripción, y en todo caso las siguientes:"));
        articuloArrayList.add(new Articulo("308.1.a","Infracciones muy graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","Las que impliquen un riesgo muy grave para la salud o seguridad de vidas humanas."));
        articuloArrayList.add(new Articulo("308.1.b","Infracciones muy graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","El vertido no autorizado desde buques o artefactos flotantes de productos sólidos, líquidos o gaseosos en la Zona I, interior de las aguas portuarias."));
        articuloArrayList.add(new Articulo("308.1.c","Infracciones muy graves:\nInfracciones relativas al uso del puerto y al ejercicio de actividades que se prestan en él:","La realización, sin el debido título administrativo conforme a esta ley, de cualquier tipo de obras o instalaciones en el ámbito portuario, así como el aumento de la superficie ocupada o del volumen o de la altura construidos sobre los autorizados, siempre que se hubiera desatendido el requerimiento expreso de la Autoridad Portuaria para la cesación de la conducta abusiva o que, habiéndose notificado la incoación de expediente sancionador, se hubiere persistido en tal conducta."));

        articuloArrayList.add(new Articulo("308.2.a","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","Ordenar o emprender la navegación sin que el buque reúna las debidas condiciones de navegabilidad haciendo peligrar su seguridad."));
        articuloArrayList.add(new Articulo("308.2.b","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","Las alteraciones sustanciales realizadas en la construcción de los elementos de salvamento respecto de las características de los prototipos oficialmente homologados"));
        articuloArrayList.add(new Articulo("308.2.c","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","El incumplimiento de las normas o instrucciones de las Autoridades Marítimas sobre depósito, manipulación, carga, estiba, desestiba, transporte o mantenimiento de materias explosivas o peligrosas a bordo de los buques."));
        articuloArrayList.add(new Articulo("308.2.d","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","Emplear, sin necesidad, señales de socorro y utilizar arbitrariamente signos distintivos que confieran al buque el carácter de buque hospital o cualquier otro característico en contra de lo previsto en el Derecho Internacional."));
        articuloArrayList.add(new Articulo("308.2.e","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","La falta de conocimiento o cumplimiento por parte de los miembros de la dotación de los buques españoles de pasaje de sus obligaciones y funciones atribuidas en el correspondiente cuadro orgánico para situaciones de siniestro, aprobado por la Administración de acuerdo con las normas aplicables."));
        articuloArrayList.add(new Articulo("308.2.f","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","El incumplimiento de las normas o resoluciones de la Administración en materia de dotaciones mínimas de seguridad a las que se refiere el artículo 253 de la presente ley."));
        articuloArrayList.add(new Articulo("308.2.g","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","El incumplimiento de las disposiciones reglamentarias sobre la seguridad marítima que ocasione accidentes con daños para las personas."));
        articuloArrayList.add(new Articulo("308.2.h","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","El incumplimiento de las normas o resoluciones de las Autoridades Marítimas en relación con la instalación y el desarrollo de actividades desde plataformas fijas que se encuentren en aguas situadas en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción, cuando se ponga en peligro la seguridad marítima."));
        articuloArrayList.add(new Articulo("308.2.i","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","Las acciones u omisiones del capitán, patrón del buque o práctico de servicio mientras se hallen en estado de ebriedad o bajo la influencia de sustancias psicotrópicas, drogas tóxicas o estupefacientes a consecuencia de lo cual se pueda alterar su capacidad para desempeñar sus funciones."));
        articuloArrayList.add(new Articulo("308.2.j","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","Las acciones u omisiones del capitán o de los miembros de la dotación del buque que supongan la no prestación o denegación de auxilio a las personas o buques, cuando el mismo sea solicitado o se presuma su necesidad."));
        articuloArrayList.add(new Articulo("308.2.k","Infracciones muy graves:\nInfracciones contra la seguridad marítima:","Las acciones u omisiones no comprendidas en los apartados anteriores que pongan en grave peligro la seguridad del buque o de la navegación.\n"));

        articuloArrayList.add(new Articulo("308.3.a","Infracciones muy graves:\nInfracciones contra la ordenación del tráfico marítimo:","El incumplimiento de las normas que reservan para buques de bandera española determinados tráficos o actividades conforme a lo previsto en la presente ley."));
        articuloArrayList.add(new Articulo("308.3.b","Infracciones muy graves:\nInfracciones contra la ordenación del tráfico marítimo:","El incumplimiento de las normas sobre Registro de Buques y Empresas Navieras, exportación, importación o abanderamiento provisional de buque español en favor de extranjeros o de buques extranjeros en España."));
        articuloArrayList.add(new Articulo("308.3.c","Infracciones muy graves:\nInfracciones contra la ordenación del tráfico marítimo:","El incumplimiento de las órdenes, prohibiciones o condiciones a que se refieren los artículos 297, 298, 300 y 301 de la presente ley."));
        articuloArrayList.add(new Articulo("308.3.d","Infracciones muy graves:\nInfracciones contra la ordenación del tráfico marítimo:","Prestar servicios de navegación marítima careciendo de la correspondiente concesión o autorización administrativa cuando sea exigible conforme a lo previsto en la presente ley."));
        articuloArrayList.add(new Articulo("308.3.e","Infracciones muy graves:\nInfracciones contra la ordenación del tráfico marítimo:","El falseamiento de la información que reglamentariamente se deba suministrar a las Autoridades Marítimas."));
        articuloArrayList.add(new Articulo("308.3.f","Infracciones muy graves:\nInfracciones contra la ordenación del tráfico marítimo:","El incumplimiento de las obligaciones de servicio público impuestas a las empresas navieras titulares de líneas regulares o servicios no regulares de navegación interior, de cabotaje, exterior o extranacional."));
        articuloArrayList.add(new Articulo("308.3.g","Infracciones muy graves:\nInfracciones contra la ordenación del tráfico marítimo:","La falta de cumplimiento de las obligaciones derivadas de las disposiciones que se dicten en aplicación de lo previsto en la presente ley, sobre coordinación de los Puertos del Estado y de la Marina Mercante con las necesidades de la defensa nacional y la seguridad pública."));

        articuloArrayList.add(new Articulo("308.4.a","Infracciones muy graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","La evacuación deliberada desde buques o plataformas fijas u otras construcciones que se encuentren en aguas situadas en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción, de residuos, desechos u otras materias cargadas a bordo o depositadas con tal propósito, salvo cuando se cuente con la debida autorización de vertido o ésta no sea exigible según lo previsto en la legislación específica vigente." ));
        articuloArrayList.add(new Articulo("308.4.b","Infracciones muy graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","Llevar a cabo con deliberación la contaminación del medio marino por el hundimiento de buques o la destrucción de plataformas fijas u otras construcciones que se encuentren en aguas situadas en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción, con las mismas excepciones señaladas en el párrafo anterior." ));
        articuloArrayList.add(new Articulo("308.4.c","Infracciones muy graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","La evacuación deliberada de desechos u otras materias resultante directa o indirectamente de las operaciones normales de los buques, plataformas fijas u otras construcciones que se encuentren en aguas situadas en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción, cuando tales evacuaciones se produzcan en contravención de la legislación vigente sobre la materia." ));
        articuloArrayList.add(new Articulo("308.4.d","Infracciones muy graves: Infracciones relativas a la prevención de la contaminación del medio marino producida desde buques o plataformas fijas u otras instalaciones que se encuentren en zonas en las que España ejerce soberanía, derechos soberanos o jurisdicción:","La introducción deliberada, de modo directo o indirecto en el medio marino de sustancias, materiales o formas de energía que puedan constituir un peligro para la salud humana, perjudicar los recursos turísticos, paisajísticos o biológicos y la vida marina, reducir las posibilidades de esparcimiento u obstaculizar otros usos legales de los mares, en la medida en que dicha introducción fuera contraria a la legislación vigente o no contase con la debida autorización." ));

        articuloArrayList.add(new Articulo("308.5.a","Infracciones muy graves:\nInfracciones en la prestación de servicios portuarios:","Prestación de servicios portuarios sin el debido título habilitante."));
        articuloArrayList.add(new Articulo("308.5.b","Infracciones muy graves:\nInfracciones en la prestación de servicios portuarios:","Incumplimiento grave o reiterado de las obligaciones de servicio público."));
        articuloArrayList.add(new Articulo("308.5.c","Infracciones muy graves:\nInfracciones en la prestación de servicios portuarios:","Incumplimiento de las instrucciones dictadas por los organismos portuarios, en el ámbito de sus competencias, sobre salvaguarda de la libre competencia."));
        articuloArrayList.add(new Articulo("308.5.d","Infracciones muy graves:\nInfracciones en la prestación de servicios portuarios:","ncumplimiento grave o reiterado por los titulares de las licencias de las condiciones esenciales que se les imponga."));
    }
}

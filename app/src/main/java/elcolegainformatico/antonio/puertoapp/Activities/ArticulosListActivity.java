package elcolegainformatico.antonio.puertoapp.Activities;

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

import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.Model.Infraccion;
import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 7/4/17.
 */

public class ArticulosListActivity extends AppCompatActivity {

    ListView listArticulos;
    ArrayList<Articulo> articuloArrayList;
    ArrayList<Infraccion> sancionesSaved = new ArrayList<>(); //Store sanciones go from SancionesList
    boolean isReglamento;

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
        text_custom_title.setText("LISTA DE ARTICULOS");

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


        articuloArrayList = new ArrayList<Articulo>();

        if(isReglamento==true){
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

    public void loadReglamentos(){
        articuloArrayList.add(new Articulo("Articulo 77.1","FALTAS LEVES","Desobediencia a las órdenes de los  Celadores-Guarda muelles o  de sus superiores o la interferencia en sus actuaciones o la falta al respeto a los mismos"));
        articuloArrayList.add(new Articulo("Articulo 77.2","FALTAS LEVES","Uso de las obras o instalaciones portuarias sin autorización, en  materias no especificadas en números o artículos posteriores"));
        articuloArrayList.add(new Articulo("Artículo 77.3","FALTAS LEVES","Incumplimiento de los usuarios de las reglas de aplicación de las tarifas por servicios en materia no especificadas,   en  números o artículos posteriores"));
        articuloArrayList.add(new Articulo("Artículo 77.4","FALTAS LEVES","Retraso en la presentación de datos  a efectos de la liquidación de  servicios prestados  por la Junta, cuya cuantía sea inferior a 300 EUROS"));
        articuloArrayList.add(new Articulo("Artículo 77.5","FALTAS LEVES","Defectuosa o inadecuada utilización del equipo o instalaciones portuarias en función de sus características y potencias"));
        articuloArrayList.add(new Articulo("Artículo 77.6","FALTAS LEVES","Traslado  sin  autorización  fuera  del  recinto  portuario  del utillaje de la Junta con o sin realización de trabajos"));
        articuloArrayList.add(new Articulo("Artículo 77.7","FALTAS LEVES","La ocupación de superficies, almacenes , departamentos o  locales  de  la Junta, sujetos a tarifas por Servicios Específicos, sin autorización   o  por Persona diferente a la autorizada , o su dedicación a usos diferentes a los Autorizados"));
        articuloArrayList.add(new Articulo("Artículo 77.8","FALTAS LEVES","Incumplimiento de la órdenes de desalojar superficies, almacenes, departamentos o locales de la Junta , sujetos a tarifas por Servicios Específicos por terminación del plazo, o en el fijado  de acuerdo con  las  condiciones de la tarifa "));
        articuloArrayList.add(new Articulo("Artículo 77.9","FALTAS LEVES","Abusos de  la  utilización  de  los suministros de agua dulce o salada , así como falta de conservación de las instalaciones, grifos abiertos, rotura de mangueras o similares"));
        articuloArrayList.add(new Articulo("Artículo 77.10","FALTAS LEVES","Incumplimiento de las condiciones establecidas en las autorizaciones para la prestación de servicios por personas o  entidades  diferentes  de la Junta del Puerto y que no estuviese comprendido o especificado en otro  articulo"));
        articuloArrayList.add(new Articulo("Artículo 77.11","FALTAS LEVES","Acceso de personas a las zonas acotadas o cercadas sin autorización"));
        articuloArrayList.add(new Articulo("Artículo 77.12","FALTAS LEVES","Acceso de vehículos a las zonas acotadas  o cercadas sin autorización"));
        articuloArrayList.add(new Articulo("Artículo 77.13","FALTAS LEVES","Acceso de maquinaria y vehículos industriales con medios   propios  de manipulación, a las zonas acotadas o cercadas sin autorización"));
        articuloArrayList.add(new Articulo("Artículo 77.14","FALTAS LEVES","Circulación de vehículos por la zona portuaria sin las debidas precauciones, en forma peligrosa o sin respetar las señales de tráfico o normas generales establecidas"));
        articuloArrayList.add(new Articulo("Artículo 77.15","FALTAS LEVES","Circulación de vehículos y maquinaria con carga superiores a las autorizadas tanto totales, como por eje, con ruedas,  cadenas  o  llantas metálicas, o en condiciones que supongan peligrosidad"));
        articuloArrayList.add(new Articulo("Artículo 77.16","FALTAS LEVES","Circulación de trenes  por  las  vías  existentes  en la zona portuaria sin ajustarse a la programación  acordada  o  sin autorización especial, o incumplimiento  de  los  limites de velocidad o normas de seguridad establecidos"));
        articuloArrayList.add(new Articulo("Artículo 77.17","FALTAS LEVES","Entorpecimiento del uso de escaleras y rampas o su utilización indebida"));
        articuloArrayList.add(new Articulo("Artículo 77.18","FALTAS LEVES","Aparcamiento de cualquier clase de vehículos,  sobre  las vías férreas  o de grúas a menos de dos metros del carril más próximo"));
        articuloArrayList.add(new Articulo("Artículo 77.19","FALTAS LEVES","Depósito de mercancías o cualquier clase de objetos sobre las carreteras y vías de circulación o de grúas, en zonas que impidan su normal utilización"));
        articuloArrayList.add(new Articulo("Artículo 77.20","FALTAS LEVES","Aparcamiento de vehículos o vagones en lugares diferentes de los expresamente señalizados o autorizados para ello"));
        articuloArrayList.add(new Articulo("Artículo 77.21","FALTAS LEVES","Aportación de información o datos inexactos al  solicitar la designación de atraque para un buque, que puedan inducir a resoluciones  inapropiadas"));
        articuloArrayList.add(new Articulo("Artículo 77.22","FALTAS LEVES","Falta de comunicación y de confirmación por escrito de la entrada de un buque dentro de los plazos y en la forma señalada"));
        articuloArrayList.add(new Articulo("Artículo 77.23","FALTAS LEVES","No  avisar  a  los Servicios  de Explotación de la demora en la llegada y atraque de un buque, o del  plazo  en  que  deben  quedar finalizadas las operaciones"));
        articuloArrayList.add(new Articulo("Artículo 77.24","FALTAS LEVES","Incumplimiento del ritmo fijado para la descarga o carga de un buque"));
        articuloArrayList.add(new Articulo("Artículo 77.25","FALTAS LEVES","Aportación de informaciones inexactas que puedan inducir a resoluciones inapropiadas sobre la solicitud de trabajos en horas extraordinarias."));
        articuloArrayList.add(new Articulo("Artículo 77.26","FALTAS LEVES","Negativa a realizar trabajos en horas extraordinarias, festivos o en turnos no habituales, cuando sean declarados de urgencia, por la Dirección del Puerto"));
        articuloArrayList.add(new Articulo("Artículo 77.27","FALTAS LEVES","Realizar operaciones no autorizadas de reparaciones de buques, aprovisionamientos y similares"));
        articuloArrayList.add(new Articulo("Artículo 77.28","FALTAS LEVES","No adoptar los buques las medidas necesarias para evitar los riesgos de causar daños o averías a las obras, instalaciones o utillaje portuario"));
        articuloArrayList.add(new Articulo("Artículo 77.29","FALTAS LEVES","Entorpecimiento del uso  de  las vías férreas o de grúas u otras instalaciones portuarias con escalas, puntales o elementos similares"));
        articuloArrayList.add(new Articulo("Artículo 77.30","FALTAS LEVES","Aportación de información o datos inexactos al solicitar  servicios de la Junta para la manipulación de mercancías que puedan inducir a resoluciones inapropiadas"));
        articuloArrayList.add(new Articulo("Artículo 77.31","FALTAS LEVES","Demora en la iniciación de las operaciones autorizadas, no  avisadas a los Servicios de Explotación con antelación suficiente"));
        articuloArrayList.add(new Articulo("Artículo 77.32","FALTAS LEVES","Incumplimiento en el ritmo fijado para la realización de operaciones de manipulación de mercancías"));
        articuloArrayList.add(new Articulo("Artículo 77.33","FALTAS LEVES","Manipulación de mercancías sin adoptar las precauciones necesarias para evitar averías, pérdidas o deterioros  en  las mismas, o riesgo de daños"));
        articuloArrayList.add(new Articulo("Artículo 77.34","FALTAS LEVES","Causar averías, pérdidas o deterioros a mercancías manipuladas valoradas en cuantía inferior a 300 EUROS"));
        articuloArrayList.add(new Articulo("Artículo 77.35","FALTAS LEVES","Depósito de mercancías en forma  inadecuadas  de aprovechamiento de espacio"));
        articuloArrayList.add(new Articulo("Artículo 77.36","FALTAS LEVES","Producir sobre los muelles o pavimentos cargas superiores a los límites fijados"));
        articuloArrayList.add(new Articulo("Artículo 77.37","FALTAS LEVES","No disponer las protecciones adecuadas para evitar deterioros de mercancías, pavimentos, obras o instalaciones"));
        articuloArrayList.add(new Articulo("Artículo 77.38","FALTAS LEVES","Depósito de mercancías sin autorización o fuera de las zonas designadas, o demora en el plazo fijado para la retirada o traslado de mercancías depositadas"));
        articuloArrayList.add(new Articulo("Artículo 77.39","FALTAS LEVES","Abandono de basuras , escombros  o  residuos  de cualquier clase en terrenos, instalaciones, obras o equipos portuarios, falta de  limpieza de las zonas de depósito al levantar las mercancías o cualquier hecho que afecte a la limpieza de los citados bienes"));
        articuloArrayList.add(new Articulo("Artículo 77.40","FALTAS LEVES","No tomar las precauciones necesarias para que las mercancías  depositadas no puedan producir daños a otras contiguas o situadas en  su zona de influencia"));
        articuloArrayList.add(new Articulo("Artículo 77.41","FALTAS LEVES","Retirada o intento de  retirada  de  mercancías depositadas sin haber abonado o garantizado el pago de las tarifas , gastos o sanciones que les afecten, o sin haber sido autorizado su levante"));
        articuloArrayList.add(new Articulo("Artículo 77.42","FALTAS LEVES","No cumplir las precauciones establecidas para el embarque,  tránsito o desembarque de ganado"));
        articuloArrayList.add(new Articulo("Artículo 77.43","FALTAS LEVES","Descarga de productos de pesca en muelles o  rampas  diferentes  de los habilitados, efectuar su venta fuera de  la  lonja o sitios autorizados a este fin o  contravenir  las  normas  existentes  sobre el uso  de instalaciones pesqueras  siempre  que  no estén específicamente sancionadas en reglamentos especiales"));
        articuloArrayList.add(new Articulo("Artículo 77.44","FALTAS LEVES","Realizar operaciones complementarias de clasificación, remisión formación y descomposición de unidades de carga , flejado complementario  y  otras  similares,  en  lugares  diferentes de los designados en cada caso por el personal de Celadores-Guardamuelles"));
        articuloArrayList.add(new Articulo("Artículo 77.45","FALTAS LEVES","Depósito o abandono en lugares no autorizados de utillaje y elementos auxiliares de las operaciones de manipulación de mercancías"));
        articuloArrayList.add(new Articulo("Artículo 77.46","FALTAS LEVES","Utilización de utillaje o elementos auxiliares  en  las  operaciones de manipulación de mercancías en condiciones deficientes de conservación, o en operaciones para las que no sean idóneos"));
        articuloArrayList.add(new Articulo("Artículo 77.47","FALTAS LEVES","No adopción de cualquiera de las precauciones generales citadas  en artículo 55, cuando no estén penalizadas según otros números o artículos"));
        articuloArrayList.add(new Articulo("Artículo 77.48","FALTAS LEVES","Fumar durante la manipulación de  mercancías  combustibles  o  no adoptar las precauciones ordenadas en la prevención de incendios"));
        articuloArrayList.add(new Articulo("Artículo 77.49","FALTAS LEVES","Incumplimiento de condiciones o prescripciones establecidas en las concesiones o autorizaciones administrativas cuando suponga inadecuada  utilización  del  dominio  público  o  de las correspondientes obras o instalaciones"));
        articuloArrayList.add(new Articulo("Artículo 77.50","FALTAS LEVES","Incumplimiento de las condiciones o prescripciones establecidas en concesiones o autorizaciones administrativas cuando suponga defectuosa prestación de los servicios"));
        articuloArrayList.add(new Articulo("Artículo 77.51","FALTAS LEVES","Efectuar actividades indicadas en el artículo 64 sin la previa autorización"));
        articuloArrayList.add(new Articulo("Artículo 77.52","FALTAS LEVES","Ejecución de cualquiera de las acciones prohibidas con carácter general en artículo 65 cuando no estén penalizadas según otro número o artículo"));
        articuloArrayList.add(new Articulo("Artículo 77.53","FALTAS LEVES","Causar daños a obras o instalaciones en los puertos o en  el equipo, o en los útiles o efectos, o en cualquier clase de mercancías de  los Organismo Portuario  o  de  terceros, o   sustración o hurtos de los Mismos, valoradas en cuantía inferior a 300 EUROS"));
        articuloArrayList.add(new Articulo("Artículo 77.54","FALTAS LEVES","Incumplimiento   de  órdenes  o  instrucciones de la Dirección  del Puerto, y personal en  quien  delegue  para  el   cumplimiento o interpretación de lo dispuesto en este Reglamento, y que no estuviese comprendido o especificado en otros artículos "));
        articuloArrayList.add(new Articulo("Artículo 77.55","FALTAS LEVES","Negativa o entorpecimiento a permitir la inspección por el  personal del organismo portuario de las instalaciones establecidas en la zona portuaria, o  no  aportar los  documentos  comerciales  necesarios para la instrucción de los expedientes de sanciones y realización de pruebas"));

        articuloArrayList.add(new Articulo("Artículo 78.1","FALTAS GRAVES","Utilización indebida o no autorizada de los Servicios Portuarios"));
        articuloArrayList.add(new Articulo("Artículo 78.2","FALTAS GRAVES","Presentación de informaciones o datos deformados  o  inexactos  y cualquier acción u omisión que supongan actuaciones encaminadas a defraudar en materia de tarifas o cánones"));
        articuloArrayList.add(new Articulo("Artículo 78.3","FALTAS GRAVES","Retraso en la presentación de datos  a efectos de la liquidación de los servicios prestados por la Junta, en cuantía superior a 300 EUROS."));
        articuloArrayList.add(new Articulo("Artículo 78.4","FALTAS GRAVES","No presentación de la información necesaria para la liquidación de la Tarifa por Servicios Generales, por las operaciones realizadas en muelles o instalaciones particulares o en las playas"));
        articuloArrayList.add(new Articulo("Artículo 78.5","FALTAS GRAVES","Alquiler o cesión no autorizada, directa o encubierta, del uso de almacenes, departamentos o locales de la Junta, sujetos a tarifas por Servicios específicos "));
        articuloArrayList.add(new Articulo("Artículo 78.6","FALTAS GRAVES","Realización de obras no autorizadas en almacenes,  departamentos o locales de la Junta"));
        articuloArrayList.add(new Articulo("Artículo 78.7","FALTAS GRAVES","Atraque de buques sin autorización, o en muelle distinto al designado"));
        articuloArrayList.add(new Articulo("Artículo 78.8","FALTAS GRAVES","No informar expresamente a los Servicios de la Junta de la naturaleza explosiva o peligrosa de la mercancías que transporte o vaya a descargar un buque o no cumplir las normas fijadas para su manipulación "));
        articuloArrayList.add(new Articulo("Artículo 78.9","FALTAS GRAVES","Incumplimiento  de  las órdenes cursadas  de  desatraque,  cambio de muelle o fondeo de un buque"));
        articuloArrayList.add(new Articulo("Artículo 78.10","FALTAS GRAVES","Aportación de informes inexactos en la solicitud de trabajos en horas extraordinarias que afecten al atraque de otro buque"));
        articuloArrayList.add(new Articulo("Artículo 78.11","FALTAS GRAVES","No dejar libre el atraque en el plazo fijado al finalizar las operaciones de carga o descarga de mercancías"));
        articuloArrayList.add(new Articulo("Artículo 78.12","FALTAS GRAVES","No  permanecer  en  condiciones  de  poder  desatracar, cuando así se ordene, los buque cuyo atraque se haya otorgado con  esa  condición, y todos los buques que transportan mercancías  inflamables,  explosivas o peligrosas"));
        articuloArrayList.add(new Articulo("Artículo 78.13","FALTAS GRAVES","Mantener atracado a un muelle un buque con peligro de hundimiento"));
        articuloArrayList.add(new Articulo("Artículo 78.14","FALTAS GRAVES","Efectuar la maniobra de atraque o desatraque en condiciones  que supongan peligro para las obras , instalaciones o equipo  portuario  sin tomar las precauciones necesarias"));
        articuloArrayList.add(new Articulo("Artículo 78.15","FALTAS GRAVES","Vertido por un buque de residuos o contaminación por el mismo  del espacio portuario"));
        articuloArrayList.add(new Articulo("Artículo 78.16","FALTAS GRAVES","Salida o intento de salida de un buque, sin haber abonado o garantizado ante la Junta del Puerto las cantidades adeudadas por aplicación de tarifas o valoración de las averías causadas"));
        articuloArrayList.add(new Articulo("Artículo 78.17","FALTAS GRAVES","Causar averías, pérdidas  o  deterioros a mercancías manipuladas valoradas en cuantía superior a 300 EUROS"));
        articuloArrayList.add(new Articulo("Artículo 78.18","FALTAS GRAVES","Depositar mercancías explosivas, inflamables  o  peligrosas en espacios diferentes de los designados"));
        articuloArrayList.add(new Articulo("Artículo 78.19","FALTAS GRAVES","Producir sobre los muelles o pavimentos cargas superiores a  los  límites fijados, con peligro para la seguridad de las obras o instalaciones portuarias"));
        articuloArrayList.add(new Articulo("Artículo 78.20","FALTAS GRAVES","No informar expresamente a los servicios de la Junta de la naturaleza explosiva, inflamable o peligrosa de las mercancías que se vayan a manejar o no cumplir  las  normas  fijadas para su manipulación o depósito"));
        articuloArrayList.add(new Articulo("Artículo 78.21","FALTAS GRAVES","Utilización de utillaje o elementos auxiliares en  las  operaciones de mercancías en condiciones deficientes de seguridad "));
        articuloArrayList.add(new Articulo("Artículo 78.22","FALTAS GRAVES","Fumar, encender  fuegos o  situar  luces  sin protección en zonas en que se manipulen o estén depositadas mercancías  inflamables o explosivas"));
        articuloArrayList.add(new Articulo("Artículo 78.23","FALTAS GRAVES","Realización de obras o instalaciones en la zona portuaria sin la debida concesión o autorización"));
        articuloArrayList.add(new Articulo("Artículo 78.24","FALTAS GRAVES","Ocupación de terrenos o ejecución de obras o instalaciones provisionales sin autorización"));
        articuloArrayList.add(new Articulo("Artículo 78.25","FALTAS GRAVES","Incumplimiento de condiciones o prescripciones establecidas en las concesiones o autorizaciones administrativas cuando suponga deterioro del dominio público, modificación esencial  de  la  utilización permitida del mismo o de las correspondientes obras o instalaciones "));
        articuloArrayList.add(new Articulo("Artículo 78.26","FALTAS GRAVES","Incumplimiento de las condiciones o prescripciones establecidas en concesiones o autorizaciones administrativas  cuando suponga perjuicios a terceros como consecuencia de la defectuosa prestación de los servicios, si no figura sanción expresa en las citadas condiciones o prescripciones "));
        articuloArrayList.add(new Articulo("Artículo 78.27","FALTAS GRAVES","Contaminación del espacio terrestre o marítimo de la zona portuaria o, en general, del medio ambiente, del puerto con cualquier clase de depósitos, vertido, emanaciones  o  ruidos  sin los  tratamientos  adecuados debidamente autorizados"));
        articuloArrayList.add(new Articulo("Artículo 78.28","FALTAS GRAVES","Realización de trabajos, prestación de servicios, ejercicio de cualquier clase de actividades industriales o comerciales  en  la  zona portuaria sin la debida autorización"));
        articuloArrayList.add(new Articulo("Artículo 78.29","FALTAS GRAVES","Ejercicio en el Puerto de actividades de Consignatario o naviero, Empresas estibadoras  de carga y descarga, vendedores  o  exportadores de pescado, y otras que supongan funciones análogas sin estar inscritos en el censo correspondiente, así  como  el  incumplimiento de las normas dictadas para su actuación en la zona portuaria"));
        articuloArrayList.add(new Articulo("Artículo 78.30","FALTAS GRAVES","Facturación indebida o incorrecta por servicios prestados o repercusión de servicios formulados por la Junta o por otros Organismos de la Administración"));
        articuloArrayList.add(new Articulo("Artículo 78.31","FALTAS GRAVES","Causar directamente daños en los terrenos, obras o instalaciones del Puerto, o en el equipo, o en los útiles o efectos, o  en  cualquier tipo De mercancías del Organismo Portuario o de terceros, o hurto o robo De las mismas, valoradas en cuantía superior a 300 EUROS"));
        articuloArrayList.add(new Articulo("Artículo 78.32","FALTAS GRAVES","Ofensas o injurias de palabras u obra a los Celadores-Guardamuelles o a sus superiores en acto de servicio"));
        articuloArrayList.add(new Articulo("Artículo 78.33","FALTAS GRAVES","Cualquier infracción leve que constituya peligro  para  las  personas o de las  que  se  deriven daños o perjuicios no superiores a 500.000 Pesetas"));
        articuloArrayList.add(new Articulo("Artículo 78.34","FALTAS GRAVES","Comisión de infracción leve de la que derive una lesión menos grave para alguna persona"));
        articuloArrayList.add(new Articulo("Artículo 78.35","FALTAS GRAVES","Reincidencia en las faltas leves señaladas en los números 8,21,28,46,49 y 50 del artículo anterior, o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción"));
        articuloArrayList.add(new Articulo("Artículo 78.36","FALTAS GRAVES","Reincidencia de las faltas leves señaladas en los números 1,3,5,7,15,19,24,25,27,30,32,34,35,38,41,53,54 y 55 del artículo anterior, o  incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción  "));
        articuloArrayList.add(new Articulo("Artículo 78.37","FALTAS GRAVES","Reincidencia de las faltas leves señaladas en los  restantes  números del artículo anterior, o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción"));

        articuloArrayList.add(new Articulo("Artículo 79.1","FALTAS MUY GRAVES","Cualquiera de las infracciones leves o graves, definidas en los artículos anteriores, que ocasionaran lesión grave a alguna persona o daños o perjuicios superiores a 500.000 Pesetas"));
        articuloArrayList.add(new Articulo("Artículo 79.2","FALTAS MUY GRAVES","Reincidencia de las faltas graves señaladas  en los   números 6, 23 y 25 del artículo anterior , o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción "));
        articuloArrayList.add(new Articulo("Artículo 79.3","FALTAS MUY GRAVES","Reincidencia de las faltas graves señaladas en los números 2,5,7,8,9,1316,18,20,24,26,27,30 y 32 del artículo anterior o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción"));
        articuloArrayList.add(new Articulo("Artículo 79.4","FALTAS MUY GRAVES","Reincidencia de las faltas graves señaladas en los restantes números del artículo anterior, o incumplimiento de las órdenes tendentes a rectificar en el plazo fijado los hechos que provocaron la sanción"));

    }

    public void loadLeyes(){
        articuloArrayList.add(new Articulo("Articulo 1","FUNDAMENTO LEGAL","Este Reglamento cumplimenta y desarrolla lo dispuesto en los Artículos 31 de la Ley de Puertos de 19 de Enero de 1928 y 63 del Reglamento para su ejecución, en la Ley 27/1968 de 20 de junio, sobre Junta de Puertos y Estatuto de Autonomía , demás disposiciones concordantes con esta Ley y Decreto 2358/1975 de 11 de septiembre sobre sanciones en materia portuaria."));
        articuloArrayList.add(new Articulo("Articulo 2","TERRITORIAL Y FUNCIONAL","Este Reglamento es de aplicación en la Zona de servicio del Puerto de Ceuta y su objeto es la ordenación y régimen de los servicios prestados por la Junta del Puerto , la vigilancia y control  de los servicios prestados por personas y entidades diferentes del organismo portuario, el cumplimiento de las normas y condiciones fijadas para la ocupación del dominio publico para el uso de instalaciones y para el ejercicio de actividades comerciales e industriales en dicha zona , así como la fijación , cumplimiento y sanción de las normas de policía correspondientes. Están sujetos a este Reglamento las personas o entidades relacionadas con el párrafo anterior y todas las personas , vehículos,maquinaria, instalaciones, materiales y mercancías que se encuentren , incluso circunstancialmente , en la zona de servicio del Puerto."));
        articuloArrayList.add(new Articulo("Articulo 3","MINISTERIO DE OBRAS PUBLICAS\n Y JUNTA DEL PUERTO","Corresponde al Ministerio de Obras Públicas según establece los Artículos 19 y 20 de la Ley de Puertos , y a la Junta del Puerto , en cuanto en régimen de descentralización le encomienda expresamente la Ley de Junta de Puertos y Estatuto de Autonomía, el establecimiento de los servicios complementarios y especiales ,reparación ,conservación, limpieza , servicio y policía del puerto, en todo lo civil , así como la regularización de las operaciones de carga , descarga, depósito y transporte de mercancía , el acceso y circulación de personas y vehículos en su zona de servicio y cuanto se refiere al uso de las obras , utillaje e instalaciones destinadas a la explotación del Puerto."));
        articuloArrayList.add(new Articulo("Articulo 4","DIRECCIÓN DEL PUERTO","Las funciones a que se refiere el articulo anterior serán ejercidas por el Ingeniero Director del Puerto, adaptándose a las prescripciones de este Reglamento y a lo dispuesto en la Ley de Juntas , en la Ley de Puertos y sus reglamentos y Decreto de 29 –11-1932 sobre atribuciones de los Ingenieros Directores de Puertos."));
        articuloArrayList.add(new Articulo("Articulo 5","OTROS MINISTERIOS","Tienen  jurisdicción propia en la zona de servicio del Puerto , de modo permanente, de acuerdo con sus reglamentos y en lo que les es privativo, las Autoridades de Marina, Aduanas, Trabajo , Sanidad, Comercio (Soivre), Agricultura (servicio fitopatológico) Policía Gubernativa y Guardia Civil, que se hayan respecto a la Dirección del Puerto, en relación de mutuo auxilio y cooperación para la mejor defensa de los intereses generales a todos encomendados.Cualquier otra Autoridad que precisara efectuar una acción o intervención permanente sobre personas o cosas dentro de la zona de servicio del puerto , precisará de la oportuna autorización del Ministerio de Obras Públicas , sin perjuicio de otras autorizaciones que sean necesarias. Las actuaciones accidentales se podrán previamente en conocimiento de la Dirección del Puerto."));
        articuloArrayList.add(new Articulo("Articulo 6","VIGILANCIA Y POLICIA DEL PUERTO","La jefatura inmediata y directa de los servicios de vigilancia y policía en los muelles y zona de servicio del Puerto, será ejercida por el Director del Puerto, que podrá delegarla en el Ingeniero Jefe de la Sección de Planificación, Explotación y Obras que tendrá a sus órdenes al Comisario del Puerto, del que a su vez dependerá el personal del servicio de Celadores-Guarda muelles investidos de la condición de Agentes de la Autoridad , con calidad de Guardas Jurados , con misión de prevenir, evitar y denunciar las infracciones que puedan cometerse sobre lo dispuesto en este Reglamento, mantener el orden debido , velar porque no sufran daño las obras , materiales o mercancías  existentes en el Puerto , cumpliendo y haciendo cumplir las órdenes de servicio que le sean transmitidas por sus superiores, así como controlar los servicios prestados."));
        articuloArrayList.add(new Articulo("Articulo 7","USO DE LAS OBRAS PORTUARIAS","Están destinados al servicio público los muelles, tinglados, almacenes, armamento, caminos y terrenos y en general todas las obras e instalaciones dentro de la zona de Servicio del Puerto, con sujeción a las normas de este Reglamento, para el embarque, desembarque, trasbordo y tránsito  de pasajeros y mercancías , el depósito provisional de estas y las operaciones complementarias que sean necesarias , no permitiéndose hacer uso de dichas obras para ningún otro objeto sin la autorización exigida en cada caso por las disposiciones vigentes. Se exceptúan las autorizaciones o concesiones otorgadas en exclusiva para uso particular."));
        articuloArrayList.add(new Articulo("Articulo 8","SERVICIOS PRESTADOS POR LA JUNTA","Los servicios establecidos por la Junta del Puerto , se regirán por las Tarifas y Reglas de Aplicación correspondientes reglamentariamente aprobadas, considerándose las mismas como formando parte de este Reglamento. La Dirección del Puerto podrá exigir el depósito previo del importe aproximado de los servicios solicitados, procediéndose a su tramitación a la liquidación de los mismos , con abono y devolución de las diferencias ."));
        articuloArrayList.add(new Articulo("Articulo 9","SERVICIOS PRESTADOS POR OTRAS PERSONAS O ENTIDADES ","Los servicios que tengan que prestarse por personas o entidades diferentes de la Junta del Puerto , deberán ser previamente autorizados y estarán sujetos a lo dispuesto en este Reglamento y a las condiciones que se fijen en la correspondiente autorización"));
        articuloArrayList.add(new Articulo("Articulo 10","NORMAS PARA EL ACCESO\n A LOS MUELLES ","Con arreglo a lo que dispone el Decreto de 11-12-1942 , queda limitado el acceso a los muelles y zonas de Tráfico de los Puertos a las personas y vehículos que por razón de sus funciones o servicios de los mismos, están debidamente autorizados. Corresponde a las Autoridades de Marina conceder estas autorizaciones a los pasajeros y demás personas que hayan de subir a bordo de los barcos, así como a los tripulantes de las embarcaciones en Puerto. Compete a la Dirección del Puerto conceder las autorizaciones para el acceso de toda clase vehículos y para el de las personas que hayan de intervenir en la ejecución y conservación de obras e instalaciones , en operaciones de carga y descarga , circulación sobre los muelles y en cuanto a se refiere al uso de las diversas obras destinadas en las operaciones del Puerto. El personal no funcionario que dependa de las autoridades citadas en el Artº.5º, será dotado de la documentación suficiente por los jefes de sus servicios respectivos , debiendo exhibir dicha documentación , ante los Celadores-Guarda muelles cuando sean requerido para ello. Todas estas autorizaciones se otorgarán sin perjuicio de las atribuciones que competen a la Dirección General de Seguridad en el ejercicio de sus funciones."));
        articuloArrayList.add(new Articulo("Articulo 11","FUNCIONARIOS Y AUTORIDADES","Para los Jefes y Oficiales de  Tierra, Mar  y a los funcionarios del Estado y de la Junta del Puerto y Cónsules acreditados en Ceuta, servirá de autorización su cartilla o carné de identidad . Las Autoridad solo necesitarán darse a conocer a los Agentes encargados de la Vigilancia del Puerto."));
        articuloArrayList.add(new Articulo("Articulo 12","ZONA DE LIBRE CIRCULACIÓN","Será libre la circulación del público en las zonas a que se refiere el Artº 5º del Decreto de 11/12/1942, situadas fuera de los cerramientos o de las zonas acotadas de los muelles. De acuerdo con lo dispuesto en el Artº 7º de la Ley de Puertos, y disposiciones complementarias  sobre la materia . Dichas zonas serán fijadas por la Junta del Puerto a propuesta del Ingeniero Director."));
        articuloArrayList.add(new Articulo("Articulo 13","VEHÍCULOS INDUSTRIALES \nY MAQUINARIA","El acceso de vehículos industriales y de maquinaria móvil , se autorizará en cada caso por la Dirección del Puerto , que podrá controlar sus características y estado de conservación y funcionamiento de acuerdo con lo que se establece en este Reglamento, sin que por ello pueda derivarse ninguna responsabilidad para la Junta del Puerto ni para su personal , en caso de accidente"));
        articuloArrayList.add(new Articulo("Articulo 14","VEHÍCULOS","Los vehículos de toda clase que circulen por el Puerto , deberán hacerlo con las debidas precauciones y respetando las señales de tráfico existentes. Al cruzar las vías o detenerse sobre ellas para tomar o dejar carga  en los casos en que ello no esté expresamente prohibido por la Dirección del Puerto , lo harán con el conductor dispuesto a retirarlo de la vía tan pronto sea necesario. Las cargas unitarias por eje, así con la presión de inflado de los neumáticos , no serán superiores a las fijadas para las carreteras nacionales o las regulaciones especificas que dicte la Dirección del Puerto. Los tubos de escape de los motores de explosión serán de tipo cerrado o con protección antideflagrante . Se prohíbe la circulación de vehículos ligeros (turismos, motos, etc. ) por la zona de carga y descarga, entendiendo por tal la que está bajo el radio de acción de las grúas y demás instalaciones para la manipulación de mercancía . No se permite marchar a los vehículos por las zonas generales de circulación de los muelles y carreteras de servicio a velocidad superior a 40 Km/h., ni por otros sitios que por las vías destinadas a su tránsito. No se permitirá la circulación de ninguna clase de vehículos con llanta metálica  o de madera. Serán de obligado cumplimiento las normas establecidas en el Código de Circulación, en los caminos, accesos y zonas destinadas a aquella finalidad y las normas particulares que a estos efectos puedan dictarse por la Dirección del Puerto. Los Celadores-Guardamuelles ordenarán la circulación de vehículos y peatones haciendo cumplir las normas citadas en los párrafos anteriores "));
        articuloArrayList.add(new Articulo("Articulo 15","FERROCARRILES","Las vías férreas establecida en los muelles  por la Junta del Puerto son propiedad del Estado y la circulación por ellas y su explotación están sujetas a las prescripciones de este Reglamento, a las Leyes y Reglamentos de Ferrocarriles y a las disposiciones especiales que por el Ministerio de Obras Públicas  se dicten, teniendo la Dirección del Puerto, dentro de este , las atribuciones que le otorgan las disposiciones vigentes .La programación de la circulación de los trenes por las vías existentes en la zona portuaria, tanto propiedad de la Junta como las instaladas en virtud de concesiones o autorizaciones administrativas , será establecida por la Dirección del Puerto, proporcionándose bien con carácter general o en cada caso concreto, por los propietarios de material móvil , la información necesaria con la suficiente antelación. Los trenes o locomotoras recorrerán las vías a velocidad máxima de 10 Km/h, quedando prohibido lanzar vagones sueltos."));
        articuloArrayList.add(new Articulo("Articulo 16","USO DE ESCALERAS","Las escaleras de los muelles están destinadas exclusivamente al embarque y desembarque de personas y equipajes y pequeñas operaciones autorizadas, estando prohibido, en absoluto, interrumpir el libre paso por ellas y utilizarla para cualquier otro fin diferente de los citados."));
        articuloArrayList.add(new Articulo("Articulo 17","DEPOSITOS Y APARCAMIENTOS","Se prohíbe terminantemente el aparcamiento de vehículos y depósito de mercancías u objetos sobre las vías férreas o de grúas a menos de dos metros del carril mas próximo. Queda prohibido depositar sobre las vías de circulación cualquier clase de mercancía u objetos , aún provisionalmente o por poco tiempo. Tampoco se permitirá dejar sin autorización ,sobre los muelles cualquier clase de vehículos, maquinarias, útiles o materiales utilizados en las operaciones, que deberán ser retirados tan pronto cese su empleo y aparcados o depositados en los lugares previamente designados. Bajo ningún concepto las mercancías podrán ser depositadas en lugares de los muelles en que impidan o dificulten la libre circulación de las grúas existentes. El aparcamiento de vehículos o vagones quedará limitado exclusivamente a las zonas señalizadas a estos efectos. Los vehículos, objetos o cosas que se encuentren indebidamente aparcados o depositados infringiendo las normas anteriores , podrán ser retirados por los servicios del Puerto por cuenta y riesgo de su propietario, sin perjuicio de las sanciones que procedan. Para su retirada deberán abonar o garantizar previamente el importe de los gastos ocasionados , de las sanciones impuestas y de las tarifas devengadas."));
        articuloArrayList.add(new Articulo("Articulo 18","ATRAQUES","Los atraques de los buques se regularán con carácter general por lo dispuesto en los artículos  29º y 30º del Reglamento para la vigente Ley de Puertos "));
        articuloArrayList.add(new Articulo("Articulo 19","SOLICITUD DE ENTRADA Y ATRAQUE ","Lo Armadores o consignatarios de buques comunicarán por escrito a la Comisaría del Puerto (Servicio de Explotación) la próxima entrada de cada buque en aguas del Puerto formulando además en su caso , la petición de atraque suministrando para la mas correcta programación de los atraques la información necesaria, que contendrá, además de los datos relativos al buque y a la mercancía que transporta y se vaya a manipular en el Puerto, la fecha de llegada del buque y la de su probable salida. La empresa estibadora que se propone para efectuar las operaciones y las necesidades de utillaje , avituallamiento, servicios especiales, y superficie de depósito. Dichos representantes  del buque confirmarán al indicado servicio dentro de las 6 horas hábiles siguientes, la fecha y hora de la entrada del buque en las aguas del Puerto así como la de su salida."));
        articuloArrayList.add(new Articulo("Articulo 20","PROGRAMACIÓN Y DESIGNACIÓN DE ATRAQUES","La programación conjunta de las operaciones del Puerto se realizará con la mayor antelación posible, preferentemente con carácter semanal designándose diariamente los puntos de los muelles en que cada buque deberá realizar las operaciones de movimiento de pasajeros, de carga y descarga de vehículos y mercancías y las de avituallamiento y trasbordos . De estas designaciones se dará cuenta a la Autoridad de Marina  para que se ordene el atraque y amarre de los buques . No se permitirá realizar operaciones de movimiento de pasajeros ni de carga y descarga , ni se prestarán servicios , a los buques que no hayan atracado en el lugar designado."));
        articuloArrayList.add(new Articulo("Articulo 21","RECTIFICACIÓN DE LA DESIGNACIÓN DE ATRAQUE","Si a juicio de la Autoridad de Marina del Puerto y por razones de escasez de espacio o calado intranquilidad de las aguas o fuerza del viento, no fuesen adecuadas  a las condiciones del buque las del punto designado para el atraque , la Dirección del Puerto designará nuevo punto de los muelles, si fuese posible , para realizar directamente el embarque, desembarque o trasbordo."));
        articuloArrayList.add(new Articulo("Articulo 22","FONDEO DE BUQUES","Cuando la carga o descarga no pueda realizarse directamente en los muelles , la Dirección del Puerto lo advertirá a la Autoridad de Marina quien designará el sitio y forma en que deben de fondear los buques, procurando mientras sea posible , se hallen próximos a las zonas del muelle en que las embarcaciones auxiliares hayan de realizar el embarque o desembarque . Compete igualmente a la Autoridad de Marina la facultad de designar el sitio en que deben de fondear las embarcaciones que no se hallen a la carga o a la descarga."));
        articuloArrayList.add(new Articulo("Articulo 23","NORMAS PARA DESIGNACIÓN","La Dirección del Puerto tendrá en cuenta para la designación de los atraques, las características del buque , la existencia de concesiones o autorizaciones de superficies o instalaciones en exclusiva o preferenciales , la especializaciones de los muelles para las distintas clases de buques y carga, así como la existencia de instalación y equipo adecuado para las operaciones a realizar, y de superficies o almacenes necesarios para el depósito de las mercancías. Asimismo , la Dirección del Puerto considerará a los efectos anteriores el volumen y naturaleza del pasaje , de los vehículos y de las mercancías y la conveniencia , o no, de dar preferencia a la carga sobre la descarga , a fin de descongestionar el muelle o las instalaciones. Se procurará que los atraques designados estén lo mas cerca posible de las instalaciones existentes o de las zonas asignadas para el depósito de las mercancías , sin que esta precaución limite las facultades de los servicios del Puerto, para la programación conjunta de las operaciones que consideren mas convenientes. Los buques nacionales de líneas regulares de cabotaje y de líneas exteriores de pasaje , gozarán de la preferencias de atraque que les concede la Ley de protección y renovación de la flota Mercante Española de 12 de mayo de 1958. "));



    }
}

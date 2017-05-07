package elcolegainformatico.antonio.puertoapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import elcolegainformatico.antonio.puertoapp.Model.Articulo;
import elcolegainformatico.antonio.puertoapp.Model.Sancion;
import elcolegainformatico.antonio.puertoapp.R;

/**
 * Created by antonio on 7/4/17.
 */

public class ArticulosListActivity extends AppCompatActivity {

    ListView listArticulos;
    ArrayList<Articulo> articuloArrayList;
    ArrayList<Sancion> sancionesSaved = new ArrayList<>(); //Store sanciones go from SancionesList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Lista de articulos");

        //Esto lo usamos en la versión sin FireBase, tenemos que pasarla a la siguiente activity
        sancionesSaved = (ArrayList<Sancion>) getIntent().getSerializableExtra("sancionesSaved");

        setContentView(R.layout.activity_articulos_list); //le asigno su layout
        listArticulos=(ListView) findViewById(R.id.articulos_list); //Busco mi listView activity_articulos_list

        //Meto a manubrio los articulos
        loadArticulos();

        ArticulosAdapter myAdaptater = new ArticulosAdapter(articuloArrayList,ArticulosListActivity.this.getApplicationContext());
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

    public void loadArticulos(){

        articuloArrayList = new ArrayList<Articulo>();

        articuloArrayList.add(new Articulo("Articulo 77.1","FALTAS LEVES","Desobediencia a las órdenes de los  Celadores-Guarda muelles o  de sus superiores o la interferencia en sus actuaciones o la falta al respeto a los mismos"));
        articuloArrayList.add(new Articulo("Articulo 77.2","FALTAS LEVES","Uso de las obras o instalaciones portuarias sin autorización, en  materias no especificadas en números o artículos posteriores"));
        articuloArrayList.add(new Articulo("Artículo 77.3","FALTAS LEVES","Incumplimiento de los usuarios de las reglas de aplicación de las tarifas por servicios en materia no especificadas,   en  números o artículos posteriores"));
        articuloArrayList.add(new Articulo("Artículo 77.4","FALTAS LEVES","Retraso en la presentación de datos  a efectos de la liquidación de  servicios prestados  por la Junta, cuya cuantía sea inferior a 50.000 Pesetas"));
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
        articuloArrayList.add(new Articulo("Artículo 77.34","FALTAS LEVES","Causar averías, pérdidas o deterioros a mercancías manipuladas valoradas en cuantía inferior a 50.000 Pesetas"));
        articuloArrayList.add(new Articulo("Artículo 77.35","FALTAS LEVES","Depósito de mercancías en forma  inadecuadas  de aprovechamiento de espacio"));
        articuloArrayList.add(new Articulo("Artículo 77.36","FALTAS LEVES","Producir sobre los muelles o pavimentos cargas superiores a los límites fijados"));
        articuloArrayList.add(new Articulo("Artículo 77.37","FALTAS LEVES","No disponer las protecciones adecuadas para evitar deterioros de mercancías, pavimentos, obras o instalaciones"));
        articuloArrayList.add(new Articulo("Artículo 77.38","FALTAS LEVES","Depósito de mercancías sin autorización o fuera de las zonas designadas, o demora en el plazo fijado para la retirada o traslado de mercancías depositadas"));
        articuloArrayList.add(new Articulo("Artículo 77.39","FALTAS LEVES","Abandono de basuras , escombros  o  residuos  de cualquier clase en terrenos, instalaciones, obras o equipos portuarios, falta de  limpieza de las zonas de depósito al levantar las mercancías o cualquier hecho que afecte a la limpieza de los citados bienes "));
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
        articuloArrayList.add(new Articulo("Artículo 77.53","FALTAS LEVES","Causar daños a obras o instalaciones en los puertos o en  el equipo, o en los útiles o efectos, o en cualquier clase de mercancías de  los Organismo Portuario  o  de  terceros, o   sustración o hurtos de los Mismos, valoradas en cuantía inferior a 50.000 Pesetas"));
        articuloArrayList.add(new Articulo("Artículo 77.54","FALTAS LEVES","Incumplimiento   de  órdenes  o  instrucciones de la Dirección  del Puerto, y personal en  quien  delegue  para  el   cumplimiento o interpretación de lo dispuesto en este Reglamento, y que no estuviese comprendido o especificado en otros artículos "));
        articuloArrayList.add(new Articulo("Artículo 77.55","FALTAS LEVES","Negativa o entorpecimiento a permitir la inspección por el  personal del organismo portuario de las instalaciones establecidas en la zona portuaria, o  no  aportar los  documentos  comerciales  necesarios para la instrucción de los expedientes de sanciones y realización de pruebas"));

        articuloArrayList.add(new Articulo("Artículo 78.1","FALTAS GRAVES","Utilización indebida o no autorizada de los Servicios Portuarios"));
        articuloArrayList.add(new Articulo("Artículo 78.2","FALTAS GRAVES","Presentación de informaciones o datos deformados  o  inexactos  y cualquier acción u omisión que supongan actuaciones encaminadas a defraudar en materia de tarifas o cánones"));
        articuloArrayList.add(new Articulo("Artículo 78.3","FALTAS GRAVES","Retraso en la presentación de datos  a efectos de la liquidación de los servicios prestados por la Junta, en cuantía superior a 50.000 Pesetas."));
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
        articuloArrayList.add(new Articulo("Artículo 78.17","FALTAS GRAVES","Causar averías, pérdidas  o  deterioros a mercancías manipuladas valoradas en cuantía superior a 50.000 Pesetas"));
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
        articuloArrayList.add(new Articulo("Artículo 78.31","FALTAS GRAVES","Causar directamente daños en los terrenos, obras o instalaciones del Puerto, o en el equipo, o en los útiles o efectos, o  en  cualquier tipo De mercancías del Organismo Portuario o de terceros, o hurto o robo De las mismas, valoradas en cuantía superior a 50.000 Pesetas"));
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
}

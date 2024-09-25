package br.edu.up.planner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.up.planner.ui.screens.chaveiro.TelaProjetos
import br.edu.up.planner.ui.screens.financas.TelaFinancas
import br.edu.up.planner.ui.screens.pedido.pedidoNav
import br.edu.up.planner.ui.screens.tarefas.TarefasNavHost
import br.edu.up.planner.ui.theme.CorDoTitulo
import kotlinx.coroutines.launch
import androidx.compose.material3.OutlinedTextField as OutlinedTextField1
import androidx.compose.material3.Text as Text1

object PlannerRotas {
    val TELA_TAREFAS_ROTA = "tela_um"
    val TELA_PROJETOS_ROTA = "tela_dois"
    val TELA_FINANCAS_ROTA = "tela_tres"
    val TELA_PEDIDOS_ROTA = "tela_quatro"

}
@Preview(
    device = Devices.PIXEL
)
    @Composable
    fun PlannerNavDrawer() {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val navCtrlDrawer = rememberNavController()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(navCtrlDrawer, drawerState)
            },
            content = {
                NavHost(
                    navController = navCtrlDrawer,
                    startDestination = PlannerRotas.TELA_TAREFAS_ROTA
                ) {
                    composable(PlannerRotas.TELA_TAREFAS_ROTA) {
                        TarefasNavHost(drawerState)
                    }
                    composable(PlannerRotas.TELA_PROJETOS_ROTA) {
                        TelaProjetos(drawerState)
                    }
                    composable(PlannerRotas.TELA_FINANCAS_ROTA) {
                        TelaFinancas(drawerState)
                    }
                    composable(PlannerRotas.TELA_PEDIDOS_ROTA) {
                        pedidoNav(drawerState)
                    }
                }
            }
        )
    }


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun pedidoNav(drawerState: DrawerState) {

    var nomeCliente by remember { mutableStateOf("") }
    var tipoSapato by remember { mutableStateOf("") }
    var tarefa by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xFFF0F0F0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()

        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "=",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp))
                }
            },
            title = {
                Text(text = "Sapataria e Chaveiro Botânico ", fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    modifier = Modifier.padding(20.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(CorDoTitulo)
        )

        Text1(text = "Adicionar Pedido", fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField1(
            value = nomeCliente,
            onValueChange = { nomeCliente = it },
            label = { Text1(text = "Nome do Cliente") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo para o tipo de sapato
        OutlinedTextField1(
            value = tipoSapato,
            onValueChange = { tipoSapato = it },
            label = { Text1(text = "Tipo de Sapato") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField1(
            value = tarefa,
            onValueChange = { tarefa = it },
            label = { Text1("O que precisa fazer") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            colors = TextFieldDefaults.run {
                outlinedTextFieldColors(
                        focusedLabelColor = Color.Blue,
                        unfocusedLabelColor = Color.Gray,
                    )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { /* Ação para adicionar o pedido */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3D72D)),
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text1("Adicionar Pedido")
        }
    }
}

@Composable
    private fun DrawerContent(
        navController: NavController,
        drawerState: DrawerState
    ) {
        val coroutineScope = rememberCoroutineScope()
        val currentBack by navController.currentBackStackEntryAsState()
        val rotaAtual = currentBack?.destination?.route ?: PlannerRotas.TELA_TAREFAS_ROTA

        val ehRotaUm = rotaAtual == PlannerRotas.TELA_TAREFAS_ROTA
        val ehRotaDois = rotaAtual == PlannerRotas.TELA_PROJETOS_ROTA
        val ehRotaTres = rotaAtual == PlannerRotas.TELA_FINANCAS_ROTA

        Column(
            modifier = Modifier
                .width(300.dp)
                .background(Color.White)
                .padding(30.dp)
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = getColorMenu(ehRotaUm)
                ),
                onClick = {
                    navController.navigate(PlannerRotas.TELA_TAREFAS_ROTA)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.sapato),
                    contentDescription = "Sapataria",
                    modifier = Modifier.size(80.dp).padding(10.dp),
                    tint = getColorTexto(ehRotaUm)
                )
                Text1(text = "Sapataria", fontSize = 30.sp, color = getColorTexto(ehRotaUm))
            }

            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = getColorMenu(ehRotaDois)
                ),
                onClick = {
                    navController.navigate(PlannerRotas.TELA_PROJETOS_ROTA)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.chave),
                    contentDescription = "Chaveiro",
                    modifier = Modifier.size(80.dp).padding(10.dp),
                    tint = getColorTexto(ehRotaDois)
                )
                Text1(text = "Chaveiro", fontSize = 30.sp, color = getColorTexto(ehRotaDois))
            }

            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = getColorMenu(ehRotaTres)
                ),
                onClick = {
                    navController.navigate(PlannerRotas.TELA_FINANCAS_ROTA)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.cifrao),
                    contentDescription = "Finanças",
                    modifier = Modifier.size(80.dp).padding(10.dp),
                    tint = getColorTexto(ehRotaTres)
                )
                Text1(text = "Finanças", fontSize = 30.sp, color = getColorTexto(ehRotaTres))
            }

            // Novo botão para a tela de pedidos
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                onClick = {
                    navController.navigate(PlannerRotas.TELA_PEDIDOS_ROTA)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.pedido),
                    contentDescription = "Pedido",
                    modifier = Modifier.size(80.dp).padding(10.dp),
                    tint = Color.DarkGray
                )
                Text1(text = "Pedido", fontSize = 30.sp, color = Color.DarkGray)
            }
        }
    }

    fun getColorMenu(estaSelecionada: Boolean): Color {
        return if (estaSelecionada) {
            Color.Yellow
        } else {
            Color.Transparent
        }
    }

    fun getColorTexto(estaSelecionada: Boolean): Color {
        return if (estaSelecionada) {
            Color.Black
        } else {
            Color.DarkGray
        }
    }


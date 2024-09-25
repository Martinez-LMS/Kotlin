package br.edu.up.planner.ui.screens.pedido

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object TelaTres {
    val TELA_AFAZERES_ROUTE = "t1a"

}

@Composable
fun pedidoNav(drawerState: DrawerState, navCtrlBottonNav: NavHostController) {

    val navCtrlBottonNav = rememberNavController()
    NavHost(
        navController = navCtrlBottonNav,
        startDestination = TelaTres.TELA_AFAZERES_ROUTE
    ) {
        composable(TelaTres.TELA_AFAZERES_ROUTE) {
            pedidoNav(drawerState, navCtrlBottonNav)
        }

    }
}




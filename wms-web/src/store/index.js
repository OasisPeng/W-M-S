import vue from 'vue'
import Vuex from 'vuex'
import router,{resetRouter} from "../router"
import createPersistedState from 'vuex-persistedstate'
vue.use(Vuex)

function addNewRoute(menuList) {
    let routes = router.options.routes
    console.log(routes)
    routes.forEach(routeItem=>{
        if(routeItem.path=="/Index"){
            menuList.forEach(menu=>{
                let childRoute={
                    path:'/'+menu.menuClick,
                    name:menu.menuName,
                    meta:{
                        title:menu.menuName
                    },
                    component:()=>import('../components/'+menu.menuComponent)
                }
                routeItem.children.push(childRoute)//添加路由
            })
        }
    })
    resetRouter()
    router.addRoutes(routes)
}

//vuex状态管理
export default new Vuex.Store({
    state:{
        menu:[]
    },
    mutations:{
        setMenu(state,menuList){
            state.menu=menuList
            addNewRoute(menuList)
        },
        setRouter(state,menuList){
            addNewRoute(menuList)
        }
    },
    getters:{
        getMenu(state){
            return state.menu
        }
    },
    plugins:[createPersistedState()]
})
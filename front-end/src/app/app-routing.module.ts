import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";

import { HomePageComponent } from "./pages/home-page/home-page.component";
import { ProductPageComponent } from "./pages/product-page/product-page.component";
import { ContactPageComponent } from "./pages/contact-page/contact-page.component";
import { AboutPageComponent } from "./pages/about-page/about-page.component";
import { ErrorPageComponent } from "./pages/error-page/error-page.component";
import { ProductDetailPageComponent } from "./pages/product-detail-page/product-detail-page.component";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { RegisterPageComponent } from "./pages/register-page/register-page.component";
import { AuthGuard } from "./guard/auth.guard";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent},
  { path: 'products', component: ProductPageComponent, canActivate: [AuthGuard] },
  { path: 'product-detail/:id', component: ProductDetailPageComponent, canActivate: [AuthGuard] },
  { path: 'about', component: AboutPageComponent, canActivate: [AuthGuard] },
  { path: 'contact', component: ContactPageComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginPageComponent},
  { path: 'register', component: RegisterPageComponent},
  { path: '**', component: ErrorPageComponent}
]

// '**' wildcard route - should always be last, it matches from top to the bottom
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})

export class AppRoutingModule {

}
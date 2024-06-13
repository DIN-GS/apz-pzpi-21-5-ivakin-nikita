import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path: 'header', component: HeaderComponent},
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { TokenService } from "./token/token.service";


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private tokenService: TokenService, private router: Router) {}

  canActivate(): boolean {
    try {
      const role = this.tokenService.getRoleFromToken();
      return !!role;
    } catch (error) {
      this.router.navigate(['/login']);
      return false;
    }
  }
}

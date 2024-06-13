import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  constructor(private translateService: TranslateService) {
    const storedLang = localStorage.getItem('appLanguage') || (navigator.language || 'en').split('-')[0];
    this.translateService.setDefaultLang(storedLang);
    this.translateService.use(storedLang);
  }

  public setLanguage(lang: string) {
    this.translateService.use(lang);
    localStorage.setItem('appLanguage', lang); 
  }

}

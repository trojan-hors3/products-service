import { PackageService } from './../package.service';
import { Package } from './../package';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {
  package: Package = new Package();

  constructor(private route: ActivatedRoute,
              private packageService: PackageService) {}

  ngOnInit() {
    this.getPackage();
  }

  getPackage() {
    const packageId = this.route.snapshot.paramMap.get('id');

    this
    .packageService
    .getPackage(packageId)
    .subscribe(p => this.package = p);
  }

}

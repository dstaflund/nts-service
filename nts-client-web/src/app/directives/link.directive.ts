import { Directive, HostBinding, Inject, Input, OnInit } from '@angular/core';

const browser = typeof window !== 'undefined';

// See https://kevinphelps.me/blog/2017-06-30/handling-external-links-in-angular
@Directive({ selector: 'a' })
export class LinkDirective implements OnInit {
  @Input() href: string;
  @HostBinding('rel') rel: string;
  @HostBinding('target') target: string;

  constructor() { }

  ngOnInit() {
    if (this.href !== undefined && this.href.startsWith('http')) {
      this.rel = this.rel || 'noopener noreferrer';
      this.target = this.target || '_blank';
    }

    if (browser && this.rel === undefined) {
      this.rel = ''; // prevent browser from setting `rel="undefined"`
    }

    if (browser && this.target === undefined) {
      this.target = ''; // prevent browser from setting `target="undefined"`
    }
  }
}

import {AuthConfig} from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
  issuer: 'http://localhost:7080/realms/oauth-demo-app',
  redirectUri: window.location.origin,
  clientId: 'front-app-client',
  responseType: 'code',
  strictDiscoveryDocumentValidation: false,
  scope: 'openid profile email offline_access',
}

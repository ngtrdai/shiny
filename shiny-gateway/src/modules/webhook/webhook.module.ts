import { Module } from '@nestjs/common';
import { IdentityController } from './identity/identity.controller';

@Module({
  controllers: [IdentityController],
})
export class WebhookModule {}

export interface SecureStoragePluginInterface {
  get: (options: { key: string; accessibility?: string }) => Promise<{ value: string }>;
  getValues: (options: { keys: string[]; accessibility?: string }) => Promise<any>;
  getAll: (options: { accessibility?: string }) => Promise<any>;
  set: (options: { key: string; value: string; accessibility?: string }) => Promise<{ value: boolean }>;
  remove: (options: { key: string; accessibility?: string }) => Promise<{ value: boolean }>;
  clear: () => Promise<{ value: boolean }>;
  keys: () => Promise<{ value: string[] }>;
  getPlatform: () => Promise<{ value: string }>;
}

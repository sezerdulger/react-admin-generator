import musiclessonDataProvider from "./musiclesson/provider/musiclessonDataProvider";

const dataProviders = [
  { dataProvider: musiclessonDataProvider, resources: ['musiclesson'] },
];

export default (type, resource, params) => {

  const dataProviderMapping = dataProviders.find(dp => dp.resources.includes(resource));
  let t
  t = type.split("_")[0].toLowerCase()
  if (type.split("_").length > 1) {
    const s = type.split("_")[1].toLowerCase()
    t += s.charAt(0).toUpperCase() + s.slice(1)
  }
  
  return dataProviderMapping.dataProvider[t](resource, params);
};
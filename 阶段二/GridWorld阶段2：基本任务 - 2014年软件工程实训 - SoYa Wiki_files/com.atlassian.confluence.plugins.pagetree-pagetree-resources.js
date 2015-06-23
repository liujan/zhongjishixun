jQuery(function(A){AJS.pagetree={MAX_DEPTH:999,MODE_EXPAND:true,MODE_COLLAPSE:false,treeRequests:{},targetPages:{},getContextPath:function(){return contextPath
},toggleChild:function(G,F,B,E){if(F==undefined||F==null){F=!this.getMode(G)
}if(B==undefined||B==null){B=0
}if(!this.isSimilarState(G,F)){var D=A("#children"+G);
if(this.hasChild(D)){var C=A("#plusminus"+G);
if(F==this.MODE_EXPAND){C.removeClass("icon-plus");
C.addClass("icon-minus")
}else{C.removeClass("icon-minus");
C.addClass("icon-plus")
}if(F==this.MODE_EXPAND){D.slideDown(300)
}else{D.slideUp(300)
}if(E){this.finishLoadingMessage(G)
}}else{this.loadChildren(G,new Array(),B,"",E)
}}if(E){this.finishLoadingMessage(G)
}},hasChild:function(B){return B.children("ul[id]").length>0
},getMode:function(C){var B=A("#plusminus"+C);
return(B.length>0)?B.hasClass("icon-minus"):this.MODE_COLLAPSE
},isSimilarState:function(C,B){return this.getMode(C)==B
},expandAll:function(B){this.doExpandCollapseAll(B,this.MODE_EXPAND)
},collapseAll:function(B){this.doExpandCollapseAll(B,this.MODE_COLLAPSE)
},doExpandCollapseAll:function(E,C){this.startLoadingMessage(E);
var D=A("#"+E);
var B=D.find("div.plugin_pagetree_children_container");
B.each(function(F){var G=AJS.pagetree.getIdFromElementName(this.id);
AJS.pagetree.toggleChild(G,C,AJS.pagetree.MAX_DEPTH,F==B.length-1)
})
},getIdFromElementName:function(B){if(!B||B==undefined){return null
}if(B.indexOf("plusminus")!=-1){return B.substring("plusminus".length)
}if(B.indexOf("children")!=-1){return B.substring("children".length)
}return null
},getTreeIdFromElementId:function(B){if(!B||B==undefined){return null
}return this.parseId(B)[1]
},parseId:function(B){if(!B||B==undefined){return null
}return B.split("-")
},startLoadingMessage:function(C){var B=this.getTreeIdFromElementId(C);
A("div.plugin_pagetree").each(function(D){if(D==B){A(this).find("span.plugin_pagetree_status").removeClass("hidden");
A(this).find("div.plugin_pagetree_expandcollapse").addClass("hidden")
}})
},finishLoadingMessage:function(C){var B=this.getTreeIdFromElementId(C);
A("div.plugin_pagetree").each(function(D){if(D==B){A(this).find("span.plugin_pagetree_status").addClass("hidden");
A(this).find("div.plugin_pagetree_expandcollapse").removeClass("hidden")
}})
},generateRequestString:function(G,B,D,C,F){var E=this.treeRequests[G];
if(B=="Orphan"){E+="&hasRoot=false&spaceKey="+F
}else{E+="&hasRoot=true&pageId="+B
}E+="&treeId="+G+"&startDepth="+C;
A.each(D,function(){E+="&ancestors="+this
});
E=(AJS.params.serverUrl||"")+E;
return E
},getPageTreeDiv:function(D){var C=D;
var B=null;
A("div.plugin_pagetree").each(function(E){if(E==C){B=A(this)
}});
return B
},getPageTreeParams:function(B){var C=B.children("fieldset");
var D=new Object();
if(C.length>0){C.children("input").each(function(){D[this.name]=this.value
})
}return D
},getPageTreeAncestorIds:function(B){var D=B.children("fieldset");
var E=new Array();
if(D.length>0){var C=D.children("fieldset");
if(C.length>0){C.children("input").each(function(){E.push(this.value)
})
}}return E
},makePlusMinusButtonsClickable:function(B){B.find("a.plugin_pagetree_childtoggle").each(function(){var C=A(this);
C.attr("href","#");
C.click(function(F){var D=C.parent().parent().children("div.plugin_pagetree_children_container");
var E=D.attr("id");
var G=E.substring(8);
AJS.pagetree.toggleChild(G);
return AJS.stopEvent(F)
})
})
},isChildrenHtml:function(B){var C=A(document.createElement("div"));
C.html(B);
return C.find("ul[id^='child_ul']").length
},loadChildren:function(J,C,N,L,F){var M=J;
var G=F;
var D=this.getContextPath();
var B=this.parseId(J);
var E=B[0];
var I=B[1];
var H=A("#children"+J);
var K=this.getPageTreeParams(this.getPageTreeDiv(I));
H.html("<ul>"+K["i18n-pagetree.loading"]+"</ul>");
A.ajax({type:"GET",url:this.generateRequestString(I,E,C,N,L),dataType:"text",success:function(O){if(AJS.pagetree.isChildrenHtml(O)){H.html(O);
if(H.children().length&&H.hasClass("hidden")){H.removeClass("hidden")
}AJS.pagetree.makePlusMinusButtonsClickable(H);
A("#plusminus"+M).addClass("icon-minus");
A("#plusminus"+M).removeClass("icon-plus");
A("#childrenspan"+AJS.pagetree.targetPages[parseInt(I)]+"-"+I).css("font-weight","bold");
if(G){AJS.pagetree.finishLoadingMessage(M)
}AJS.pagetree.hideEmptyChildrenContainers(H);
if(AJS.PageGadget&&AJS.PageGadget.contentsUpdated){AJS.PageGadget.contentsUpdated()
}}else{window.location=K.loginUrl
}},error:function(O){if(O.status=="403"){H.text(K["i18n-pagetree.error.permission"])
}else{H.text(K["i18n-pagetree.error.general"])
}}})
},hideEmptyChildrenContainers:function(B){A("div.plugin_pagetree_children_container:empty",B).addClass("hidden")
},initPageTree:function(B,F){var D=this.getPageTreeParams(B);
var G=D.noRoot=="true";
var C=G?"Orphan-"+F:D.rootPageId+"-"+F;
this.treeRequests[F]=D.treeRequestId;
this.targetPages[F]=AJS.params.pageId;
B.children("fieldset").each(function(){var H=A(this);
H.children("input[treeId]").attr("value",F);
H.children("input[rootPage]").attr("value",C)
});
if(G){B.find("div.plugin_pagetree_children").attr("id","childrenOrphan-"+F);
B.find("a.plugin_pagetree_expandall").click(function(){AJS.pagetree.expandAll("childrenOrphan-"+F);
return false
});
B.find("a.plugin_pagetree_collapseall").click(function(){AJS.pagetree.collapseAll("childrenOrphan-"+F);
return false
})
}else{B.find("div.plugin_pagetree_children").attr("id","children"+C);
B.find("a.plugin_pagetree_expandall").click(function(){AJS.pagetree.expandAll("children"+C);
return false
});
B.find("a.plugin_pagetree_collapseall").click(function(){AJS.pagetree.collapseAll("children"+C);
return false
})
}var E=this.getPageTreeAncestorIds(B);
this.loadChildren(C,E,D.startDepth,D.spaceKey)
},initPageTrees:function(){A("div.plugin_pagetree").each(function(B){AJS.pagetree.initPageTree(A(this),B)
});
this.avoidBeingFocusedByBodyLoadFunction()
},avoidBeingFocusedByBodyLoadFunction:function(){var B=self.placeFocus;
if(B){self.placeFocus=function(){var C=A("form[name='pagetreesearchform']");
C.attr("name","inlinecommentform");
B();
C.attr("name","pagetreesearchform")
}
}}};
AJS.pagetree.initPageTrees()
});
